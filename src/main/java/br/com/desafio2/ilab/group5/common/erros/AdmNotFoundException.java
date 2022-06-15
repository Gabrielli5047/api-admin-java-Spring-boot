package br.com.desafio2.ilab.group5.common.erros;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdmNotFoundException extends NoSuchElementException{
	private static final long serialVersionUID = 1L;

	public AdmNotFoundException() {
		super("Administrdor não encontrado.");
	}
}
