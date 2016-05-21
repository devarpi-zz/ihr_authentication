package org.istream.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.istream.filters.SessionInfo;
import org.istream.user.bo.UserBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

public class RegistrationAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, SessionAware,
		ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2718743594339734527L;
	private static final Logger logger = LoggerFactory
			.getLogger(RegistrationAction.class);

	private String firstName;
	private String lastName;
	private String email;

	// DI via Spring
	UserBo userBo;

	public UserBo getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	public String execute() throws Exception {

		logger.debug("Entering Action :: ");
		userBo.printUser();

		return SUCCESS;

	}

	public String register() throws Exception {
		logger.debug("Entering Action :: ");

		return SUCCESS;
	}

	public String dashboard() throws Exception {
		logger.debug("Entering Action :: ");
		SessionInfo sessionInfo = (SessionInfo) request.getSession()
				.getAttribute("SESSION_INFO");
		setFirstName(sessionInfo.getFirstName());
		setLastName(sessionInfo.getLastName());
		setEmail(sessionInfo.getEmail());

		return SUCCESS;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	protected ServletContext context;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map session;

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}