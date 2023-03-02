package com.einstein.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.einstein.feign.BackendFeign;
import com.einstein.model.AuthenticationResponse;

//@Component
public class RequestFilter extends OncePerRequestFilter{
	@Autowired
	private BackendFeign backendfeign;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub


		String path = request.getRequestURI();
		logger.info(path);
	    if ("/user/getusername".equals(path)||"/user/saveuser".equals(path)||"/register".equals(path)||"/authenticate".equals(path)||"/validate".equals(path)) {
	    	filterChain.doFilter(request, response);
	    	return;
	    }
	    
	    logger.info("backend filter");

		
		String jwtRequestHeader = request.getHeader("Authorization");
		AuthenticationResponse authResponse = backendfeign.validateJwt(jwtRequestHeader);
		
		System.out.println(authResponse);
		//logger.info("Inside JwtRequestFilter : " + request.getRequestURI());
		if(authResponse.isValid()==false) {
			
			response.reset();
		    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		

		filterChain.doFilter(request, response);
	}
	

}
