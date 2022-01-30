package com.microservice.command.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.command.entities.Product;
import com.microservice.command.services.ProductService;

@RestController
@RequestMapping("/command")
public class ProductController {
	

	private ProductService service;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.service = productService;
	}
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		return this.service.add(product);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
		return this.service.update(product, id);
	}
	
	@DeleteMapping("/product/{id}")
	public int deleteProduct(@PathVariable String id) {
		return this.service.delete(id);
	}
}
