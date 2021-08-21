package br.com.franzim.ciss.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.franzim.ciss.model.Funcionario;
import br.com.franzim.ciss.model.form.FuncionarioForm;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FuncionarioRepositoryTest {
	
	@Autowired
	private FuncionarioRepository repository;
	private String inputNome;
	private String inputSobrenome;
	private String inputEmail;
	private String inputPis;
	
	@Test
	public void deveriaCadastrarUmFuncionario() {
		Funcionario funcionario = repository.save( Funcionario.getInstance( criaFuncionario() ) );
		
		Assert.assertNotNull( funcionario );
		
		Assert.assertEquals( criaFuncionario().getNome(), funcionario.getNome() );
		Assert.assertEquals( criaFuncionario().getSobrenome(), funcionario.getSobrenome() );
		Assert.assertEquals( criaFuncionario().getEmail(), funcionario.getEmail() );
		Assert.assertEquals( criaFuncionario().getPis(), funcionario.getPis() );
	}
	
	/*
	@Test 
	public void naoDeveriaCadastrarUmFuncionarioSemNome() {
		Assert.assertNotNull( repository.save( Funcionario.getInstance( criaFuncionarioNomeFail( "" ) ) ) );
	}
	*/
	
	/*
	@Test 
	public void naoDeveriaCadastrarUmFuncionarioSemSobrenome() {
		Assert.assertNotNull( repository.save( Funcionario.getInstance( criaFuncionarioSobreNomeFail( "" ) ) ) );
	}
	*/	 
	
	/*
	@Test 
	public void naoDeveriaCadastrarUmFuncionarioEmailIncorreto() {
		Assert.assertNotNull( repository.save( Funcionario.getInstance( criaFuncionarioSobreEmailFail( "e-mail@c" ) ) ) ); 
	}
	*/
	 	
	/* 
	@Test
	public void naoDeveriaCadastrarUmFuncionarioPisIncorreto() {
		Assert.assertNotNull( repository.save( Funcionario.getInstance( criaFuncionarioSobrePisFail( "01111111111" ) ) ) );
	}
	*/
	
	public FuncionarioForm criaFuncionario() {
		inputNome = "nome";
		inputSobrenome = "sobrenome";
		inputEmail = "e-mail@ciss.com.br";
		inputPis = "01111111111";
		
		return FuncionarioForm.getInstance( inputNome, inputSobrenome, inputEmail, inputPis );
	}

	@SuppressWarnings("unused")
	private FuncionarioForm criaFuncionarioNomeFail( String input ) {
		return FuncionarioForm.getInstance( input, inputSobrenome, inputEmail, inputPis );
	}
	
	@SuppressWarnings("unused")
	private FuncionarioForm criaFuncionarioSobreNomeFail( String input ) {
		return FuncionarioForm.getInstance( inputNome, input, inputEmail, inputPis );
	}
	
	@SuppressWarnings("unused")
	private FuncionarioForm criaFuncionarioSobreEmailFail( String input ) {
		return FuncionarioForm.getInstance( inputNome, inputSobrenome, input, inputPis );
	}
	
	@SuppressWarnings("unused")
	private FuncionarioForm criaFuncionarioSobrePisFail( String input ) {
		return FuncionarioForm.getInstance( inputNome, inputSobrenome, inputEmail, input );
	}

}
