package br.com.franzim.ciss.controller.dto;

public class TokenDTO {

	private String token;
	private String tipo;
	
	protected TokenDTO() { }
	
	private TokenDTO( String token, String tipo ) {
		this.token = token;
		this.tipo = tipo;
	}

	public static TokenDTO getInstance( String token, String tipo ) { return new TokenDTO( token, tipo ); }

	public String getToken() { return this.token; }
	public String getTipo() { return this.tipo; }

}