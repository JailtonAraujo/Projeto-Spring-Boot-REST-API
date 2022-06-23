package com.projetorestapi.DTO;

import java.io.Serializable;

import com.projetorestapi.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String Authorization;
	
	public UsuarioDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.Authorization = "Bearer "+usuario.getToken();
	}
}
