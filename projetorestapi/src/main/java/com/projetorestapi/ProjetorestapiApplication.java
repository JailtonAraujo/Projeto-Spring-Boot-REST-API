package com.projetorestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProjetorestapiApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(ProjetorestapiApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//LIBERANDO OS EDNPOINTS E OS VERBOS HTTP QUE PODERÃO SER UTILIZADOS// 
		registry.addMapping("/usuario/**").allowedMethods("*")
		.allowedOrigins("http://localhost:4200","localhost:8080");//LIBERANDO PARA O USO PARA O USUARIO DEEJADO 
	}

}
