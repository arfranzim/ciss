package br.com.franzim.ciss.config.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.franzim.ciss.config.security.service.TokenService;
import br.com.franzim.ciss.model.Usuario;
import br.com.franzim.ciss.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UsuarioRepository repository;
	
	public AutenticacaoViaTokenFilter( TokenService tokenService, UsuarioRepository repository ) { 
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain )
			throws ServletException, IOException {
		
		String token = recuperarToken( request );

		if( tokenService.isTokenValid( token ) ) autenticarCliente( token );
		
		filterChain.doFilter( request, response );		
	}

	private void autenticarCliente(String token) {
		Usuario usuario = repository.findById( tokenService.getIdUsuario( token ) ).get();
		
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken( usuario, null, usuario.getAuthorities() );
		
		SecurityContextHolder.getContext().setAuthentication( authentication );
	}

	private String recuperarToken( HttpServletRequest request ) {
		
		String token = request.getHeader( "Authorization" );
		
		if( token == null || token.isEmpty() || !token.startsWith( "Bearer " ) ) return null;
		
		return token.substring( 7, token.length() );		
	}

}
