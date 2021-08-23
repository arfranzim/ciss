package br.com.franzim.ciss.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.franzim.ciss.config.security.filter.AutenticacaoViaTokenFilter;
import br.com.franzim.ciss.config.security.service.TokenService;
import br.com.franzim.ciss.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception { return super.authenticationManager(); }

	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService( autenticacaoService ).passwordEncoder( new BCryptPasswordEncoder() );
	}
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		
		http.authorizeRequests()
			.antMatchers( HttpMethod.GET, "/funcionarios" ).permitAll()
			.antMatchers( HttpMethod.GET, "/funcionarios/*" ).permitAll()
			.antMatchers( HttpMethod.POST, "/auth" ).permitAll()
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
			.and().addFilterBefore( new AutenticacaoViaTokenFilter( tokenService, usuarioRepository ), UsernamePasswordAuthenticationFilter.class );
	}
	
	@Override
	public void configure( WebSecurity web ) throws Exception {
	}
		
}
