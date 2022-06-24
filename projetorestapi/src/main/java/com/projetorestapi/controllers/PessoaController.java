package com.projetorestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetorestapi.model.Pessoa;
import com.projetorestapi.repository.PessoaRepository;


@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@PostMapping("/")
	public ResponseEntity save(@RequestBody Pessoa pessoa) {
	
		pessoaRepository.save(pessoa);
		return ResponseEntity.ok("Salvo com sucesso!");
	}
	
	@GetMapping(value="/",produces = "application/json")
	public ResponseEntity<List<Pessoa>> getAll (){
		
		List<Pessoa> pessoas = pessoaRepository.findAll();
		
		return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK);
	}
}
