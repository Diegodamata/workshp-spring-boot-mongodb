package com.diegodev.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.diegodev.workshopmongo.domain.Post;
import com.diegodev.workshopmongo.domain.User;
import com.diegodev.workshopmongo.dto.AuthorDTO;
import com.diegodev.workshopmongo.repository.PostRepository;
import com.diegodev.workshopmongo.repository.UserRepository;

@Configuration
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		//porque estou deletendo? pois como eu criei manualmente 2 usuarios no mongo preciso limpar o mongo
		//para armazenar os novos usuarios, com base em um arquivo para instanciar os obj
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		//preciso persistir os users antes de associar o author ao post senão o id ficara nulo, pois o mongo não criou ainda 
		//um id para esse usuario, e ficara nulo, por isso preciso persistir primeiro
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom Dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().add(post1);
		maria.getPosts().add(post2);
		
		userRepository.save(maria);
	}

}
