package br.com.franzim.ciss.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.franzim.ciss.model.form.FuncionarioForm;

@Entity
public class Funcionario {

	private static Funcionario INSTANCE =  new Funcionario();
    
    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    
    @NotBlank @Length( min = 2, max = 30 )
    private String nome;
    
    @NotBlank @Length( min = 2, max = 50 )
    private String sobrenome;
    
    @Email
    private String email;
    
    @Min( 11 ) @Length( min = 11, max = 11 )
    private String pis;
    
    protected Funcionario() { }
    
    private Funcionario( FuncionarioForm form ) { 
    	this.nome = form.getNome();
    	this.sobrenome = form.getSobrenome();
		this.email = form.getEmail();
		this.pis = form.getPis();
	}
    
    private Funcionario( Long id, @Valid FuncionarioForm form ) {
		this.id = id;
		this.nome = form.getNome();
		this.sobrenome = form.getSobrenome();
		this.email = form.getEmail();
		this.pis = form.getPis();
	}

	public static Funcionario getInstance() { return INSTANCE; }

    public static Funcionario getInstance( FuncionarioForm form ) { return INSTANCE = new Funcionario( form ); }
    
    public static Funcionario getInstance( Long id, @Valid FuncionarioForm form ) { return INSTANCE = new Funcionario( id, form ); }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getSobrenome() { return sobrenome; }
    public String getEmail() { return email; }
    public String getPis( ) { return pis; }
}
