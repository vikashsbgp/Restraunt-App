package com.vikash.restraunt.exception.handling;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vikash.restraunt.library.ResponseLibrary;

public class CustomAuthenticationFailureHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		ObjectMapper mapper = new ObjectMapper();
		ResponseLibrary responseLibrary = new ResponseLibrary();
		responseLibrary.setError(true);
		responseLibrary.setMessage(accessDeniedException.getMessage());
		responseLibrary.setStatus(HttpStatus.FORBIDDEN.value());
		responseLibrary.setData(null);
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
		response.getWriter().write(mapper.writeValueAsString(responseLibrary));
		
	}

	

}
