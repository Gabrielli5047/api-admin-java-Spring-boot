package br.com.desafio2.ilab.group5.common.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.desafio2.ilab.group5.model.Administrator;
import br.com.desafio2.ilab.group5.repository.AdmRepository;


public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private AdmRepository AdmRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Administrator inDB = AdmRepository.findByEmail(value).orElse(null);

        if (inDB == null) {
            return true;
        }

        return false;
    }

}
