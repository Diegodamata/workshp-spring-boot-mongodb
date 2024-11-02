package com.diegodev.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diegodev.workshopmongo.domain.User;
import com.diegodev.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService userService;

	//essa é uma outra forma de avisar que quando eu chamar meu endpoint users na requisição http get, é esse metodo que sera chamado
	//porem posso utilizar o @GetMapping
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		
		List<User> user = userService.findAll();
		
		return ResponseEntity.ok().body(user);
	}
}
