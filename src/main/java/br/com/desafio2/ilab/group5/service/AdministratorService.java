package br.com.desafio2.ilab.group5.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafio2.ilab.group5.common.erros.AdmNotFoundException;
import br.com.desafio2.ilab.group5.dto.AdminBasicInfDTO;
import br.com.desafio2.ilab.group5.dto.AdminLoginDTO;
import br.com.desafio2.ilab.group5.model.Administrator;
import br.com.desafio2.ilab.group5.repository.AdmRepository;
// import br.com.desafio2.ilab.group5.security.Encrypt;
import br.com.desafio2.ilab.group5.security.Token;
import br.com.desafio2.ilab.group5.security.TokenUtils;

@Component
@Primary
public class AdministratorService implements IAdministratorService {

	@Autowired
	private AdmRepository dao;
	
	
	@Override
	public Token createTokenAdmin(AdminLoginDTO dadosLogin) {
		try {
			Administrator user = dao.findByEmail(dadosLogin.getEmail()).orElse(null);
			if (user != null) {
				// String senhaLogin = Encrypt.encrypt(dadosLogin.getSenha());

				if (user.getPassword().equals(dadosLogin.getPassword())) {
					return new Token(TokenUtils.createToken(user));
				}
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
		}
		return null;
	}

	@Override
	public Administrator createAdmin(Administrator newAdm) {
		Administrator emailExists = dao.findByEmail(newAdm.getEmail()).orElse(null);

		if (emailExists != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email indisponível.");
		}

		try {
			Administrator newAd = dao.save(newAdm);
			return newAd;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@Override
	public List<Administrator> listAdmin() {
		List<Administrator> list = (List<Administrator>) dao.findAll();
		return list;
	}


	@Override
	public AdminBasicInfDTO findOne(Integer id) {
		Administrator adm = dao.findById(id).orElseThrow(() -> new AdmNotFoundException());
		return new AdminBasicInfDTO(adm);
	}

	@Override
	public AdminBasicInfDTO updateAdmin(Administrator updateAdm, Integer id) {
		@SuppressWarnings("unused")
		Administrator adm = dao.findById(id).orElseThrow(() -> new AdmNotFoundException());

		Administrator emailExists = dao.findByEmail(updateAdm.getEmail()).orElse(null);

		if (emailExists != null && emailExists.getId() != id) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email indisponível.");
		}

		updateAdm.setId(id);
		return new AdminBasicInfDTO(dao.save(updateAdm));
	}

	@Override
	public void deleteAdmin(Integer id) {
		Administrator adm = dao.findById(id).orElseThrow(() -> new AdmNotFoundException());
		dao.delete(adm);
	}

	@Override
	public Page<AdminBasicInfDTO> list(String size, int page) {
		if (size == null) {
			size = "5";
		}
		Pageable pagination = PageRequest.of(page, Integer.parseInt(size));
		Page<Administrator> admList = dao.findAll(pagination);
		Page<AdminBasicInfDTO> pagList = admList.map(adm -> new AdminBasicInfDTO(adm));
		return pagList;
	}

}
