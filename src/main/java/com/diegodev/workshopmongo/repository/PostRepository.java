package com.diegodev.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.diegodev.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	//esse metodo(contrato) ira retornar uma lista dos titulos dos posts
	//com essa declaração ja faz com que o spring data monte pra mim a consulta (query methods)
	List<Post> findByTittleContainingIgnoreCase(String tittle);
}
