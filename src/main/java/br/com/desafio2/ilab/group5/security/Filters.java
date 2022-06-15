package br.com.desafio2.ilab.group5.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class Filters extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			if (request.getHeader("Authorization") != null) {
				Authentication auth = TokenUtils.validate(request, response);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			filterChain.doFilter(request, response);

		} 	catch (Exception e) {
			// when this exception occurs, it will generate 401 status. Due to some trouble with the token. 
			System.out.println("Exception on Filters(Security): " + e.getMessage());
			
		}
	}

}