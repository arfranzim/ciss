package br.com.franzim.ciss.model.form;

import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.franzim.ciss.dto.FuncionarioDTO;
import br.com.franzim.ciss.model.Funcionario;

public class FuncionarioForm {

	@NotNull @Length(min = 2, max = 30)
	private String nome;
	
	@NotNull @Length(min = 2, max = 50)
    private String sobrenome;
	
	@Email
    private String email;
	
	@Enumerated
    private String pis;
	
	private FuncionarioForm() {}
	
	public static FuncionarioDTO converterToDTO( @Valid FuncionarioForm form ) { return FuncionarioDTO.converter(form); }
	
	public static Funcionario atualizar( Long id, @Valid FuncionarioForm form ) {  return Funcionario.getInstance(id, form); }

	public String getNome() { return nome; }
	public String getSobrenome() { return sobrenome; }
    public String getEmail() { return email; }
    public String getPis( ) { return pis; }
    
}
