package br.com.desafio2.ilab.group5.common.erros;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrors {
    private String message;
    private String field;
}

