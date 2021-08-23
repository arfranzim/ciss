package br.com.franzim.ciss.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.franzim.ciss.config.security.service.TokenService;
import br.com.franzim.ciss.controller.dto.TokenDTO;
import br.com.franzim.ciss.controller.form.LoginForm;

@RestController
@RequestMapping( "/auth" )
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity< TokenDTO > autenticar( @RequestBody @Valid LoginForm form ) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			String token = tokenService.gerarToken( authManager.authenticate( dadosLogin ) ); 
			return ( ResponseEntity.ok( TokenDTO.getInstance( token, "Bearer" ) ) );
		} 
		catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
