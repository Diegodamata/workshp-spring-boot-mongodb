package com.diegodev.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegodev.workshopmongo.domain.User;
import com.diegodev.workshopmongo.dto.UserDTO;
import com.diegodev.workshopmongo.repository.UserRepository;
import com.diegodev.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado com esse id: " + id));
		
	}
	
	public User insert(User obj) {
		
		return userRepository.save(obj);
		
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	//metodo para transformar um userDto para user
	public User fromDto(UserDTO obj) {
		
		return new User(obj.getId(), obj.getName(), obj.getEmail());
		
	}
	
	
}
