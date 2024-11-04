package com.diegodev.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegodev.workshopmongo.domain.Post;
import com.diegodev.workshopmongo.repository.PostRepository;
import com.diegodev.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository post;
	
	public Post findById(String id) {
		
		Optional<Post> po =  post.findById(id);
		
		if(po.isEmpty()) {
			throw new ObjectNotFoundException("Post não encontrado!");
		}
		return po.get();
	}
	
	//metodo que ira retornar uma lista de titulos dos posts, acesso o o postRepository
	//e chamo o metodo que eu defini na interface, assim pego todos os titulos dos posts
	public List<Post> findByTitle(String tittle){
	
		return post.findByTittleContainingIgnoreCase(tittle);
	}
}
