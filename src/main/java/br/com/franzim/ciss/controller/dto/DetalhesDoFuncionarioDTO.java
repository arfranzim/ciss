package br.com.franzim.ciss.controller.dto;

import br.com.franzim.ciss.dto.FuncionarioDTO;

public class DetalhesDoFuncionarioDTO {

	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	
	private DetalhesDoFuncionarioDTO( FuncionarioDTO funcionarioDTO ) {
		this.id = funcionarioDTO.getId();
		this.nome = funcionarioDTO.getNome();
		this.sobrenome = funcionarioDTO.getSobrenome();
		this.email = funcionarioDTO.getEmail();
	}
	
	public static DetalhesDoFuncionarioDTO getInstance( FuncionarioDTO funcionarioDTO ) { return new DetalhesDoFuncionarioDTO( funcionarioDTO ); }

	public Long getId() { return id; }
	public String getNome() { return nome; }
	public String getSobrenome() { return sobrenome; }
	public String getEmail() { return email; }
}
