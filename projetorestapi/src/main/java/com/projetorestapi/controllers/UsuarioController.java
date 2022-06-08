package com.projetorestapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetorestapi.model.Usuario;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> init() {
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setNome("jailton");
		usuario.setLogin("jailton10");
		usuario.setSenha("1234");
		
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
}
