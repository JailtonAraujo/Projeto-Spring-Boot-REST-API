package com.projetorestapi.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projetorestapi.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	@Query("select p from Pessoa p where p.name like ?1% ")
	public List<Pessoa> findByName(String name);
	
	default Page<Pessoa> findByNamePaginatio(String name, PageRequest pageRequest){
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setName(name);
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
				.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Example<Pessoa> exemple = Example.of(pessoa, exampleMatcher);
		
		Page<Pessoa> returno = findAll(exemple,pageRequest);
				
		return returno;
	}
		
	
}
