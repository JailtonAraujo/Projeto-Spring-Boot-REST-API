package com.projetorestapi.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.projetorestapi.AplicationContextLoad;
import com.projetorestapi.model.Usuario;
import com.projetorestapi.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenAutenticationService {

	private static final long EXPIRATION_TIME = 172800000;

	private static final String SECRET = "b2cc3dcf-817f-4c34-a58c-8ef631a3a12e";

	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	public void addAuthetication(HttpServletResponse response, String userName) throws IOException {

		String JWT = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		String token = TOKEN_PREFIX + " " + JWT;

		response.addHeader(HEADER_STRING, token);

		// Atualizando token
		AplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class).updateTokenByUsername(JWT,
				userName);
		;
		
		
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
		
	}

	public Authentication getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			String tokonClear = token.replace(TOKEN_PREFIX, "").trim();

			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(tokonClear).getBody().getSubject();

			if (user != null) {

				Usuario usuario = AplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class)
						.findByLogin(user);

				if (usuario != null) {

					if (tokonClear.equalsIgnoreCase(usuario.getToken())) {
						return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(),
								usuario.getAuthorities());
					}

				}

			}

		}

		return null; // Não Autoruzado
	}

}
