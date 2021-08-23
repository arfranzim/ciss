package br.com.franzim.ciss.config.validation;

public class ErroDeFormularioDTO {

	private String campo;
	private String erro;
	
	private ErroDeFormularioDTO( String campo, String erro ) {
		this.campo = campo;
		this.erro = erro;
	}
	
	public static ErroDeFormularioDTO getInstance( String campo, String erro ) { return new ErroDeFormularioDTO( campo, erro ); }

	public String getCampo() { return campo; }
	public String getErro() { return erro; }
	
}
