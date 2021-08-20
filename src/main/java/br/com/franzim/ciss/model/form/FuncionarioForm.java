package br.com.franzim.ciss.model.form;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.franzim.ciss.dto.FuncionarioDTO;
import br.com.franzim.ciss.model.Funcionario;

public class FuncionarioForm {
	
	private static FuncionarioForm INSTANCE =  new FuncionarioForm();

	@NotBlank @Length( min = 2, max = 30 )
	private String nome;
	    
    @NotBlank @Length( min = 2, max = 50 )
    private String sobrenome;
	    
    @Email
    private String email;
    
    @Min( 11 ) @Length( min = 11, max = 11 )
    private String pis;
	
	private FuncionarioForm() {}
	
	public static FuncionarioForm getInstance( String nome, String sobrenome, String email, String pis ) { 
		INSTANCE.nome = nome;
		INSTANCE.sobrenome = sobrenome;
		INSTANCE.email = email;
		INSTANCE.pis = pis;
		return INSTANCE; 
	}
	
	public static FuncionarioDTO converterToDTO( @Valid FuncionarioForm form ) { return FuncionarioDTO.converter(form); }
	
	public static Funcionario atualizar( Long id, @Valid FuncionarioForm form ) {  return Funcionario.getInstance(id, form); }

	public String getNome() { return nome; }
	public String getSobrenome() { return sobrenome; }
    public String getEmail() { return email; }
    public String getPis( ) { return pis; }
    
}
