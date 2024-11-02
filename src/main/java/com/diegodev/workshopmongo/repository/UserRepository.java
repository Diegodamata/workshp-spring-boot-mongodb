package com.diegodev.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.diegodev.workshopmongo.domain.User;

//component anotação informando ao spring que é um repository, com essa anotação posso injetar a dependencia em outra classe
@Repository
public interface UserRepository extends MongoRepository<User, String>{//como estou utilizando o mongo eu utilizo o repository dele
}
