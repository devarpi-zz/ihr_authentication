package org.istream;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -810770224087025522L;

	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);
	
	
	@Override	
	public String intercept(ActionInvocation invocation) throws Exception {

 		String result = null;

		try {
			result = invocation.invoke();
			HttpServletResponse response = ServletActionContext.getResponse();
			logger.error("::response.getStatus()::\r\n {}","*********** Hello Log4j2");
		}
//		} catch (SessionCreationException sEx) {
//			StringWriter stringWriter = new StringWriter();
//			PrintWriter printWriter = new PrintWriter(stringWriter);
//			sEx.printStackTrace(printWriter);
//			logger.error("{} ::ROOT CAUSE::\r\n {}",sEx.getMessage(),stringWriter.toString());
//
//			stringWriter = null;
//			printWriter = null;
//			
//			 HttpServletResponse response = ServletActionContext.getResponse();
//			 response.setContentType("text/plain");
//	         response.setStatus(503);
//			throw sEx;
//
//		}
		catch (Exception e) {
		  
		 	throw e;
		}

		return result;

	}
}
