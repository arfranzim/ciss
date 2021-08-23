package br.com.franzim.ciss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.franzim.ciss.model.Usuario;
 
public interface UsuarioRepository extends JpaRepository< Usuario, Long > {

	Optional<Usuario> findByUsername(String username);

}
