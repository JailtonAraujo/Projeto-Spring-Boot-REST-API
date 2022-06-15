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

	/* TEMPOD DE VALIDADE DO TOKEN (2 DIAS) */
	private static final long EXPIRATION_TIME = 172800000;

	/* SENHA UNICA PARA COMPOR A AUTENTICAÇÃO E AJUDAR SEGURANÇA */
	private static final String SECRET = "b2cc3dcf-817f-4c34-a58c-8ef631a3a12e";

	/* PREFIXO PADRAO DE TOKEN */
	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_STRING = "Authorization";

	/* GERANDO TOKEN DE AUTENTICAÇÃO E ADICIONANDO AO CABEÇALHO DA RESPOSRA */
	public void addAuthetication(HttpServletResponse response, String userName) throws IOException {
		/* MONTAGEM DO TOKEN */

		String JWT = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		/* JUNTANDO TOKEN COM O PREFIXO */
		String token = TOKEN_PREFIX + " " + JWT;

		/* ADICIONANDO NO CABEÇALHO HTTP */
		response.addHeader(HEADER_STRING, token);

		/* ADICIONANDO TNM NO BODY DO RESPONSE */
		response.getWriter().write("{\"Authorization\": \"" + token + "\" }");
	}

	/* RETORNA USUARIO VALIDADO COM TOKEN OU EM CASO DE NÃO RETORNA NULL */
	public Authentication getAuthentication(HttpServletRequest request) {

		/* PEGA O TOKEN ENVIANDO NO CABEÇALHO */
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {

			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();

			if (user != null) {

				Usuario usuario = AplicationContextLoad.getApplicationContext().getBean(UsuarioRepository.class)
						.findByLogin(user);

				if (usuario != null) {
					return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(), usuario.getAuthorities());
				}

			}

		}
		return null; //Não Autoruzado
	}

}
