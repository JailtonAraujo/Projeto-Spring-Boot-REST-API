package com.projetorestapi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

//Onde todas as requisições serão capturadas para autenticar
public class JWTApiAuthenticationFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//Estabelece a autenticação para requisição
		Authentication authentication = new JWTTokenAutenticationService().getAuthentication((HttpServletRequest) request, (HttpServletResponse) response);		
		
		//Coloca processo de autenticação no spring security
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//COntinua o precesso
		
		chain.doFilter(request, response);
		
	}

}
