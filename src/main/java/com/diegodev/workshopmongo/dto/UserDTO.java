package com.diegodev.workshopmongo.dto;

import java.io.Serializable;

import com.diegodev.workshopmongo.domain.User;

//classe DTO - Data Transfer Object, objeto de transferencia de dados, carrega os dados de uma entidade de forma simples
//posso pegar somente os dados necessarios dessa entidade, assim preservando dados importanter com senha por exemplo

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	public UserDTO() {
	}
	
	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
