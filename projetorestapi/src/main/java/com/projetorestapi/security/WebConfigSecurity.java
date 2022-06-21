package com.projetorestapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.projetorestapi.service.ImpleUserDatailsUserService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImpleUserDatailsUserService userDatailsUserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Ativando a proteção contra os usuarios que não estão validados por tokem//
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		/*ATIVANDO ACESSO A PAGINA INICIAL*/
		.disable().authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/index").permitAll()
		
		/*URL DE LOGOUT*/
		.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
		
		/*MAPEIA URL DE LOGOUT E INVALIDA O USUARIO*/
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		//Filtra requisições de login para autenticação
		.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),UsernamePasswordAuthenticationFilter.class)
		
		//filtra demais requisições para verificar a presença do token jwt no header http
		.addFilterBefore(new JWTApiAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Service que irá consultar o usuario no BD//
		auth.userDetailsService(userDatailsUserService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}
