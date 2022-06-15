package com.projetorestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projetorestapi.model.Usuario;
import com.projetorestapi.repository.UsuarioRepository;

@Service
public class ImpleUserDatailsUserService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*CONSULTAR NO BANCO*/
		Usuario usuario = usuarioRepository.findByLogin(username);
		if(usuario == null) {
			throw new  UsernameNotFoundException("Nome de usuario ou senha incorretos!");
		}
		
		return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
	}

}
