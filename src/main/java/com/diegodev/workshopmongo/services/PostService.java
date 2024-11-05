package com.diegodev.workshopmongo.services;

import java.util.Date;
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
	
		return post.findByTittle(tittle);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		//se eu quiser encontrar um post ate uma certa data, eu preciso adicionar 1 dia a mais, pois se eu quiser
		//encontrar uma data ate um determinado dia eu preciso, encontrar ate a meia noite daquele dia
		
		//macete para adicionar mais 24 horas na data, instancio um date, passo a minha data atual com o getTime() que é valor em milisegundos
		//e somo com 24 horas, porem preciso converter 24 hrs para milisegundos (24 * 60 * 60 * 1000)
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return post.fullSearch(text, minDate, maxDate);
	}
}
