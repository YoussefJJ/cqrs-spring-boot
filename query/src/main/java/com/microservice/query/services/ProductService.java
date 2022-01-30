package com.microservice.query.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.query.entities.Product;
import com.microservice.query.repositories.ProductRepository;

@Service
public class ProductService {
	private ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository rep) {
		this.productRepository = rep;
	}
	
	public ResponseEntity<Product> addProduct(Product product) {
		Product addedProduct = this.productRepository.save(product);
		if (addedProduct != null) {
			return new ResponseEntity<Product>(addedProduct, HttpStatus.CREATED);
		}
		return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Product> updateProduct(Product product, String id) {
		Product productToUpdate = this.productRepository.findById(id).get();
		productToUpdate.setName(product.getName());
		productToUpdate.setPrice(product.getPrice());
		productToUpdate.setQuantity(product.getQuantity());
		this.productRepository.save(productToUpdate);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	
	public ResponseEntity deleteProduct(String id) {
		Product productToDelete = this.productRepository.findById(id).get();
		if (productToDelete != null) {
			this.productRepository.delete(productToDelete);
			return new ResponseEntity<Product>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Error>(new Error("Product not found"), HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<List<Product>> getProducts() {
		Iterable<Product> products = this.productRepository.findAll();
		List<Product> result = StreamSupport.stream(products.spliterator(), false).collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(result, HttpStatus.OK);
	}
	
	public ResponseEntity<Product> getProductById(String id) {
		try {
			Product product = this.productRepository.findById(id).get();
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND); 
		}
		
	}
}


class Error {
	public Error(String message) {
		this.message = message;
	}
	String message;
}