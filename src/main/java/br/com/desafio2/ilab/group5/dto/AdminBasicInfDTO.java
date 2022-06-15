package br.com.desafio2.ilab.group5.dto;

import java.io.Serializable;

import br.com.desafio2.ilab.group5.model.Administrator;
import lombok.Data;


public @Data class AdminBasicInfDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	
	
	
	public AdminBasicInfDTO(Administrator obj) {		
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}	
	
}
