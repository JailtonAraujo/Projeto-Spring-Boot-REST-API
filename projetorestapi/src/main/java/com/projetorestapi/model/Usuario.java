package com.projetorestapi.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	
	private String login;
	
	private String senha;

}
