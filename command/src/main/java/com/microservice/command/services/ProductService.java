package com.microservice.command.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.command.entities.Product;
import com.microservice.command.events.ProductAdded;
import com.microservice.command.events.ProductDeleted;
import com.microservice.command.events.ProductUpdated;
import com.microservice.command.repositories.ProductRepository;

@Service
public class ProductService {
	private ProductRepository productRepository;
	private ProductAdded productAdded;
	private ProductUpdated productUpdated;
	private ProductDeleted productDeleted;
	
	@Autowired
	public ProductService(ProductRepository rep, ProductAdded productAdded, ProductUpdated productUpdated, ProductDeleted productDeleted) {
		this.productRepository = rep;
		this.productAdded = productAdded;
		this.productUpdated = productUpdated;
		this.productDeleted = productDeleted;
	}
	
	public ResponseEntity<Product> add(Product product) {
		this.productRepository.save(product);
		this.productAdded.send(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	public ResponseEntity<Product> update(Product product, String id) {
		Product updatedProduct = this.productRepository.findById(id).get();
		if (updatedProduct != null) {
			updatedProduct.setName(product.getName().equals("") ? updatedProduct.getName () : product.getName());
			updatedProduct.setPrice(product.getPrice() == 0.0 ? updatedProduct.getPrice() : product.getPrice());
			updatedProduct.setQuantity(product.getQuantity() == 0 ? updatedProduct.getQuantity() : product.getQuantity());
			this.productRepository.save(updatedProduct);
			this.productUpdated.send(updatedProduct);
			return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
		}
			return null;
	}
	
	public int delete(String id) {
		Product productToDelete = this.productRepository.findById(id).get();
		if (productToDelete != null) {
			this.productRepository.delete(productToDelete);
			this.productDeleted.send(productToDelete);
			return 1;
		}
		return 0;
	}
}
