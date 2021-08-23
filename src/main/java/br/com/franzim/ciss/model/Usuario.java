package br.com.franzim.ciss.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String username;
	private String senha;
	
	@ManyToMany( fetch = FetchType.EAGER )
	private List< Perfil > perfis = new ArrayList<>(0);
	
	public Long getId() { return this.id; }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { return perfis; }

	@Override
	public String getPassword() { return this.senha; }

	@Override
	public String getUsername() { return this.username; }

	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { return true; }

}
