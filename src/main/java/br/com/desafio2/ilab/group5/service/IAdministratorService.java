package br.com.desafio2.ilab.group5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.desafio2.ilab.group5.dto.AdminLoginDTO;
import br.com.desafio2.ilab.group5.dto.AdminBasicInfDTO;
import br.com.desafio2.ilab.group5.model.Administrator;
import br.com.desafio2.ilab.group5.security.Token;

public interface IAdministratorService {
    public Token createTokenAdmin(AdminLoginDTO dadosLogin);
    public Administrator createAdmin(Administrator newAdm);
    public List<Administrator> listAdmin();
    public AdminBasicInfDTO findOne(Integer id);
    public AdminBasicInfDTO updateAdmin(Administrator updateAdm, Integer id);
    public void deleteAdmin(Integer id);
    public Page<AdminBasicInfDTO> list(String size, int page);
}
