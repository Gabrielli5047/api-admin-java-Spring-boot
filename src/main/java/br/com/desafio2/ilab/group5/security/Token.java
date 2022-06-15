package br.com.desafio2.ilab.group5.security;

import lombok.Data;

public @Data class Token {
	private String token;

	public Token(String token) {		
		this.token = token;
	}	

	public String toString(){
		String[] tokenFormater = token.split(" ");
		return tokenFormater[1];
	}
}