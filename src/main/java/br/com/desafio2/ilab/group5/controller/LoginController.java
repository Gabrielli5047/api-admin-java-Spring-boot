package br.com.desafio2.ilab.group5.controller;

import java.util.concurrent.ExecutionException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafio2.ilab.group5.security.Token;
import br.com.desafio2.ilab.group5.service.IAdministratorService;
import br.com.desafio2.ilab.group5.dto.AdminLoginDTO;


@RestController
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private IAdministratorService service;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AdminLoginDTO dataLogin) throws InterruptedException, ExecutionException {
		Token token = service.createTokenAdmin(dataLogin);

		if (token != null) {
			return ResponseEntity.ok(token);
		}		
		//return ResponseEntity.status(401).body("Access denied!");
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

	}

	@GetMapping("/validateToken")
	public ResponseEntity<?> validateToken() {		
		return ResponseEntity.status(204).build();
	}
}
