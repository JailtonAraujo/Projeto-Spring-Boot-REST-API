package com.projetorestapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa) {
	
		pessoaRepository.save(pessoa);
		return new ResponseEntity<Pessoa>(HttpStatus.OK);
	}
	
	@GetMapping(value="/",produces = "application/json")
	public ResponseEntity<Page<Pessoa>> getAll (){
		
		Page<Pessoa> pessoas = pessoaRepository.findAll(PageRequest.of(0, 5));
		
		return new ResponseEntity<Page<Pessoa>>(pessoas, HttpStatus.OK);
	}
	
	@GetMapping(value="/page/{page}",produces = "application/json")
	public ResponseEntity<Page<Pessoa>> getAllPagination (@PathVariable("page") int page){
		
		Page<Pessoa> pessoas = pessoaRepository.findAll(PageRequest.of(page, 5));
		
		return new ResponseEntity<Page<Pessoa>>(pessoas, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> deletar(@PathVariable(name = "id") Integer id ){
		
		pessoaRepository.deleteById(id);
		
		return new ResponseEntity<Pessoa>(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> get (@PathVariable(name = "id") Integer id){
		
		return new ResponseEntity<Pessoa>(pessoaRepository.findById(id).get(), HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Pessoa> edit ( @RequestBody Pessoa pessoa){
		
		pessoaRepository.save(pessoa);
		
		return new ResponseEntity<Pessoa>(HttpStatus.OK);	
		}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Page<Pessoa>> findByName(@PathVariable(name = "name") String name){
		
		Page<Pessoa> list= null;
		PageRequest pageRequest = null;
		
		if(name == null || name.trim().isEmpty() || name.equalsIgnoreCase("undefined")) {
			pageRequest = PageRequest.of(0, 5);
			list = pessoaRepository.findAll(pageRequest);
		}else {
			list = pessoaRepository.findByNamePaginatio(name, pageRequest);
		}
		
		return new ResponseEntity<Page<Pessoa>>(list, HttpStatus.OK);
	}
}
