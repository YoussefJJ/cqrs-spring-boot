package com.microservice.query.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.query.entities.Product;
import com.microservice.query.services.ProductService;

@RestController
@RequestMapping("query")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("product/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable String id) {
		return this.productService.getProductById(id);
	}
	
	@GetMapping("product")
	public ResponseEntity<List<Product>> findProducts() {
		return this.productService.getProducts();
	}
	
}
