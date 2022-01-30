package com.microservice.query.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.microservice.query.entities.Product;

public interface ProductRepository extends ElasticsearchRepository<Product, String>{

}
