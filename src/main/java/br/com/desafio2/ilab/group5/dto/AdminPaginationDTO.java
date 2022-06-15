package br.com.desafio2.ilab.group5.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.desafio2.ilab.group5.model.Administrator;
import lombok.Data;

@Data
public class AdminPaginationDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int totalPages;
	private int currentPage;
	private int totalElements;
	private int size;
	private boolean isFirstPage;
	private boolean isLastPage;
	private boolean isEmpty;
	private List<AdminBasicInfDTO> content;

	public AdminPaginationDTO(Page<Administrator> pagination) {
		totalPages = pagination.getTotalPages();
		currentPage = pagination.getNumber();
		totalElements = pagination.getNumberOfElements();
		size = pagination.getSize();
		isFirstPage = pagination.isFirst();
		isLastPage = pagination.isLast();
		isEmpty = pagination.isEmpty();
		content = (List<AdminBasicInfDTO>) pagination.getContent().stream().map(admin -> new AdminBasicInfDTO(admin)).collect(Collectors.toList());		
	}
}
