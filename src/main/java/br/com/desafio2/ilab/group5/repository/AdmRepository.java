package br.com.desafio2.ilab.group5.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio2.ilab.group5.model.Administrator;

@Repository
public interface AdmRepository extends JpaRepository<Administrator, Integer> {

	Optional<Administrator> findByEmail(String email);
	Optional<Administrator> findByIdOrderByName(Integer id);
}