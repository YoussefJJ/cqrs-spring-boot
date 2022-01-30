package com.microservice.command.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.command.entities.Product;

public interface ProductRepository extends MongoRepository<Product, String>{
	
}
