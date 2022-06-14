package com.projetorestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetorestapi.model.Telefone;
import com.projetorestapi.model.Usuario;
import com.projetorestapi.repository.UsuarioRepository;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> BuscarTodos() {
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{nome}", produces = "application/json")
	public ResponseEntity<Usuario> BuscarNome (@PathVariable(name = "nome") String nome){
		
		return new ResponseEntity<Usuario>(usuarioRepository.findByName(nome), HttpStatus.OK);
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value="/", produces = "application/json")
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		
		for(int pos = 0; pos < usuario.getTelefones().size(); pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
		}
		
		usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public String deletar(@PathVariable(name = "id") Long id){
		usuarioRepository.deleteById(id);
		return  "OK";
	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<String> atualizar(@RequestBody Usuario usuario){
		
		for(int pos = 0; pos< usuario.getTelefones().size() ;pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
		}
		
		usuarioRepository.save(usuario);
		
		return new ResponseEntity<String>( "Altualizado com sucesso!", HttpStatus.OK);
	}
}
