package com.diegodev.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.diegodev.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	//--------------------------Opção 1-----------------------------------------------
	
	//pelo nome do metodo o Spring Data consegue gerar pra mim uma consulta automaticamente
	//aqui esta gerando uma lista de titulos dos posts
	//esse metodo(contrato) ira retornar uma lista dos titulos dos posts
	//com essa declaração ja faz com que o spring data monte pra mim a consulta (query methods)
	List<Post> findByTittleContainingIgnoreCase(String tittle);
	
	//--------------------------Opção 2---------------------------------------
	
	//tambem buscar o post com base no tittle, essa é uma outra forma alternativa de fazer a consulta
	//porem esse não ira conter um nome proprio do Spring Data para fazer a consulta, essa irá ser uma consultta
	//personalizada
	
	//A anotação @Query é usada no Spring Data MongoDB para 
	//definir consultas personalizadas diretamente no repositório, utilizando a sintaxe do MongoDB.
	
	//{ 'tittle': { $regex: ?0, $options: 'i' } } Esse é o formato de consulta json, Doc do mongoDb explica corretamente
	//o tittle é o valor(Propriedade) que eu estou buscando nos meus posts
	//$regex: Operador de expressão regular do MongoDB, que permite buscar valores que correspondam a um padrão específico.
	//qual padrao é esse? é o texto que estou buscando no post, é referenciado como ?0, interrogação e o numero, o 0 significa que é o primeiro parametro do metodo
	//e o $options: 'i' é opcional, informo o 'i' que serve para ignorar letras maiuscula e minuscula
	@Query("{ 'tittle': { $regex: ?0, $options: 'i' } }")
	List<Post> findByTittle(String text);
	
	//------------------------------------Opção 3 -----------------------------------
	
	//Consulta com vários critérios
	//"Buscar posts contendo um dado string em qualquer lugar (no título, corpo ou comentários) e em um dado
	//intervalo de datas"
	
	//essa consulta eu vou querer buscar um text em qualquer lugar do post, ex: titulo, comentari ou corpo
	//e tambem irei informar a data minima para esse post e a data maxima
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'tittle': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
