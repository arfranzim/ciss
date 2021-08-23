package br.com.franzim.ciss.config.security.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.franzim.ciss.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class TokenService {
	
	@Value( "${ciss.jwt.expiration}" )
	private String expiration;
	
	@Value( "${ciss.jwt.secret}" )
	private String secret;

	public String gerarToken( Authentication authentication ) {
		Usuario usuario = ( Usuario ) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date( hoje.getTime() + Long.parseLong( expiration ) );
		
		return Jwts.builder()
				.setIssuer( "API de Funcionários da CISS" )
				.setSubject( usuario.getId().toString() )
				.setIssuedAt( hoje )
				.setExpiration( dataExpiracao )
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey( this.secret ).parseClaimsJws(token);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey( this.secret ).parseClaimsJws( token ).getBody();
		return Long.parseLong( claims.getSubject() );
	}
	
}
