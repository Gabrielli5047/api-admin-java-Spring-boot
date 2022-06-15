package br.com.desafio2.ilab.group5.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "administrators")
public class Administrator {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false, length = 50)
	@Size(max = 50, message = "Limite de 50 caracteres.")
	@NotBlank(message = "O campo name é obrigatório")
	private String name;

	@Column(name = "email", nullable = false, length = 50, unique = true)
	@Size(max = 50, message = "Limite de 50 caracteres.")
	@NotBlank(message = "O campo email é obrigatório")
	private String email;

	@Column(name = "password", nullable = false, columnDefinition = "TEXT")
	@NotBlank(message = "O campo password é obrigatório")
	private String password;
}
