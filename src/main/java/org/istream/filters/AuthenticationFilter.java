package org.istream.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AuthenticationFilter implements Filter {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		SessionInfo sessionInfo = new SessionInfo();

		if (isAccessTokenValid((HttpServletRequest) request, sessionInfo)) {
			System.out.println("doFilter :Forward to DoFilter");
			chain.doFilter(request, response);
		} else {

			String code = request.getParameter("code");
			if (null == code) {

				String redirectURL = "https://accounts.google.com/o/oauth2/auth?scope=https://www.googleapis.com/auth/userinfo.profile+https://www.googleapis.com/auth/userinfo.email&state=%2Fprofile&response_type=code&client_id=70056503219-39up97ss4m38ih7o5dc6s9arsqb277t0.apps.googleusercontent.com&redirect_uri=http://ihr-env.us-east-1.elasticbeanstalk.com/secure/dashboard";
				System.out.println("Redirect URL :: " + redirectURL);
				((HttpServletResponse) response).sendRedirect(redirectURL);

			} else {

				if (isValidAuthCode(code, (HttpServletRequest) request,
						sessionInfo)) {
					if (isAccessTokenValid((HttpServletRequest) request,
							sessionInfo)) {
						System.out.println("Forward to DoFilter");
						chain.doFilter(request, response);
					}
				}
			}
		}

	}

	private String readRefreshTokenFromSession(HttpServletRequest request) {
		if (null != request.getSession().getAttribute(SESSION_INFO)) {
			SessionInfo sessionInfo = (SessionInfo) request.getSession()
					.getAttribute(SESSION_INFO);
			return sessionInfo.getRefreshToken();
		}
		return null;
	}

	private String readAccessTokenFromSession(HttpServletRequest request) {
		return  getAccessToken(request);
	}

	private boolean isValidAuthCode(String code, HttpServletRequest request,
			SessionInfo sessionInfo) {

		System.out.println("isValidAuthCodeOrGetRefreshToken : code" + code);

		if (null == code) {
			return Boolean.FALSE;
		}
		String foros = "client_id=70056503219-39up97ss4m38ih7o5dc6s9arsqb277t0.apps.googleusercontent.com"
				+ "&client_secret=sX2G5KT2DNPn1YOxd2Uljzx-"
				+ "&redirect_uri=http://ihr-env.us-east-1.elasticbeanstalk.com/secure/dashboard";

		if (null != code) {
			foros += "&code=" + code;
			foros += "&grant_type=authorization_code";
		}

		HttpClient client = new HttpClient();
		String url = "https://accounts.google.com/o/oauth2/token";
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");

		try {
			post.setRequestEntity(new StringRequestEntity(foros, null, null));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		try {
			client.executeMethod(post);
			String accessTokenResponse = post.getResponseBodyAsString();
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(accessTokenResponse);
			JSONObject parsed = (JSONObject) obj;
			System.out.println("AccessToken Response ::" + parsed);

			setAccessToken(parsed, sessionInfo, (HttpServletRequest) request);
			return Boolean.TRUE;
		} catch (HTTPException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean isAccessTokenValid(HttpServletRequest request,
			SessionInfo sessionInfo) {
		if (null != request) {
			String accessToekn = readAccessTokenFromSession(request);
			if (null == accessToekn) {
				return Boolean.FALSE;
			}

			GetMethod getUserInfo = new GetMethod(
					"https://www.googleapis.com/oauth2/v1/userinfo?access_token="
							+ accessToekn);

			try {
				HttpClient client = new HttpClient();
				client.executeMethod(getUserInfo);
				String accessTokenResponse = getUserInfo
						.getResponseBodyAsString();
				JSONParser jsonParser = new JSONParser();
				Object obj = jsonParser.parse(accessTokenResponse);

				JSONObject parsed = (JSONObject) obj;
				setUserInfo(parsed, sessionInfo, (HttpServletRequest) request);

				System.out.println("Email:: " + sessionInfo.getEmail());
				return Boolean.TRUE;
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		return Boolean.FALSE;
	}

	private void setAccessToken(JSONObject parsed, SessionInfo sessionInfo,
			HttpServletRequest request) {
		if (null != request.getSession().getAttribute(SESSION_INFO)) {
			request.getSession().removeAttribute(SESSION_INFO);
		}
		sessionInfo.setAccessToken((String) parsed.get("access_token"));
		 
		request.getSession().setAttribute("ACCESS_TOKEN", sessionInfo.getAccessToken());
	}
	
	private String getAccessToken(HttpServletRequest request) {
		if(request.getSession().getAttribute("ACCESS_TOKEN") != null) {
			return (String)  request.getSession().getAttribute("ACCESS_TOKEN");
		}
		return null;
	}

	private void setUserInfo(JSONObject parsed, SessionInfo sessionInfo,
			HttpServletRequest request) {
		if (null != request.getSession().getAttribute(SESSION_INFO)) {
			request.getSession().removeAttribute(SESSION_INFO);
		}
		sessionInfo.setFirstName((String) parsed.get("given_name"));
		sessionInfo.setLastName((String) parsed.get("family_name"));
		sessionInfo.setEmail((String) parsed.get("email"));
		sessionInfo.setDisplayName((String) parsed.get("name"));

		request.getSession().setAttribute(SESSION_INFO, sessionInfo);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	final static String SESSION_INFO = "SESSION_INFO";
}
