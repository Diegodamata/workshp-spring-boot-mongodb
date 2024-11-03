package com.diegodev.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//como estou trabalhando com o mongoDB banco orientado a documentos informo que essa classe é um document

//essa anotação faz com que eu indico que essa classe é um documento detro de uma coleção do mongo
@Document(collection = "user") //e eu chamo a coleção que eu criei no mongoDB que é o user, porem se eu não colocar a collection ele pego o nome da classe em minuscula
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String email;
	
	//no spring data para eu informar que uma coleção esta referenciando uma outra coleção eu uso o DBRef
	//o lazy = true quer dizer que como eu to referenciando um coleção, é ruim que busque todos os usuario e trazer pra mim
	//todos os posts desse usuario geraria um trafego da rede desnecessario,
	//então eu utilizo essa anotação para garantir que o post vão ser carregados se eu explicitamente acessalos
	@DBRef(lazy = true) 
	private List<Post> posts = new ArrayList<>();
	
	public User() {
	}

	public User(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
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
	
	public List<Post> getPosts() {
		return posts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
