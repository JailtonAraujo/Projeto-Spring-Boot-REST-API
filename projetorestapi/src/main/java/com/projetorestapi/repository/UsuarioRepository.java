package com.projetorestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projetorestapi.model.Usuario;

@Transactional
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("select u from Usuario u where u.nome = ?1")
	public Usuario findByName(String nome);
	
	
	@Query("select u from Usuario u where u.login = ?1")
	public Usuario findByLogin(String login);

}
