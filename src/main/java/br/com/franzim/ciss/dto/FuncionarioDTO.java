package br.com.franzim.ciss.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.franzim.ciss.model.Funcionario;
import br.com.franzim.ciss.model.form.FuncionarioForm;

public class FuncionarioDTO {

	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String pis;
	
	private FuncionarioDTO() {}

	private FuncionarioDTO( FuncionarioForm form ) { this.nome = form.getNome(); }

	private FuncionarioDTO( Funcionario funcionario ) { 
		this.id = funcionario.getId();
		this.nome = funcionario.getNome();
		this.sobrenome = funcionario.getSobrenome();
		this.email = funcionario.getEmail();
		this.pis = funcionario.getPis();
	}
	
	public static FuncionarioDTO getInstance( FuncionarioForm form ) { return new FuncionarioDTO( form ); }
	
	public static FuncionarioDTO converter( FuncionarioForm form ) { return getInstance( form ); }
	
	public static FuncionarioDTO converterByModel( Funcionario funcionario ) { return new FuncionarioDTO( funcionario ); }

	public static List< FuncionarioDTO > converterToListDTO( List< Funcionario > lst ) {
		return lst.stream().map( a -> converterByModel( a ) ).collect( Collectors.toList() );
	}
		
	public Long getId() { return id; }
	public String getNome() { return nome; }
	public String getSobrenome() { return sobrenome; }
    public String getEmail() { return email; }
    public String getPis( ) { return pis; }
}
