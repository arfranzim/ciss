package br.com.franzim.ciss.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.franzim.ciss.controller.dto.DetalhesDoFuncionarioDTO;
import br.com.franzim.ciss.dto.FuncionarioDTO;
import br.com.franzim.ciss.model.Funcionario;
import br.com.franzim.ciss.model.form.FuncionarioForm;
import br.com.franzim.ciss.repository.FuncionarioRepository;

@RestController
@RequestMapping( "/funcionarios" )
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository repository; 

	@GetMapping 
	public List< FuncionarioDTO > listar() { return FuncionarioDTO.converterToListDTO( repository.findAll() ); }
	
	@GetMapping( "/{id}" )
	public ResponseEntity< DetalhesDoFuncionarioDTO > detalhar( @PathVariable Long id ) {	
		
		if( repository.findById( id ).isPresent() )
			return ResponseEntity.ok( DetalhesDoFuncionarioDTO.getInstance( FuncionarioDTO.converterByModel( repository.findById( id ).get() ) ) );
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity< FuncionarioDTO > cadastrar( @RequestBody @Valid FuncionarioForm form, UriComponentsBuilder uriBuilder ) {
		
		Funcionario funcionario = repository.save( Funcionario.getInstance( form ) );
		
		URI uri = uriBuilder.path( "/funcionarios/{id}" ).buildAndExpand( funcionario.getId( ) ).toUri();
		
		return ResponseEntity.created(uri).body( FuncionarioDTO.converterByModel( funcionario ) );
	}
	
	@PatchMapping( "/{id}" )
	@Transactional
	public ResponseEntity< FuncionarioDTO > atualizar( @PathVariable Long id, @RequestBody @Valid FuncionarioForm form ) {
		
		if( repository.findById( id ).isPresent() ) 
			return ResponseEntity.ok( FuncionarioDTO.converterByModel( repository.save( FuncionarioForm.atualizar( id, form ) ) ) ); 
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping( "/{id}" )
	public ResponseEntity< ? > remover( @PathVariable Long id ) {
		
		if( repository.findById( id ).isPresent() ) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();		
	}
	
}
