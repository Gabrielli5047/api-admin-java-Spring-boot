package br.com.desafio2.ilab.group5.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio2.ilab.group5.dto.AdminBasicInfDTO;
import br.com.desafio2.ilab.group5.model.Administrator;
import br.com.desafio2.ilab.group5.service.IAdministratorService;

@RestController
@CrossOrigin("*")
@RequestMapping("/administrators")
public class AdministratorController {

	@Autowired
	private IAdministratorService service;

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Administrator adm) {

		return ResponseEntity.status(201).body(new AdminBasicInfDTO(service.createAdmin(adm)));
	}

	@GetMapping
	public ResponseEntity<Page<AdminBasicInfDTO>> list(@RequestParam(name = "size", required = false) String size,
			@RequestParam(name = "page") int page) {
		return ResponseEntity.ok().body(service.list(size, page));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdminBasicInfDTO> findOne(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.findOne(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AdminBasicInfDTO> update(@Valid @RequestBody Administrator adm, @PathVariable Integer id) {
		return ResponseEntity.ok().body(service.updateAdmin(adm, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		service.deleteAdmin(id);
		return ResponseEntity.status(204).build();
	}

}
