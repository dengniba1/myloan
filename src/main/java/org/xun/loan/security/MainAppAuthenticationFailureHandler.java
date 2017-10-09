package org.xun.loan.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class MainAppAuthenticationFailureHandler implements AuthenticationFailureHandler{

	private static Logger LOGGER = Logger.getLogger(MainAppAuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		LOGGER.info("login failed "+ exception.getMessage());
		response.setCharacterEncoding("utf-8");
		response.sendRedirect("/logins");
	}

}
