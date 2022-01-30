package com.microservice.query.events;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.microservice.query.entities.Product;
import com.microservice.query.services.ProductService;

@Component
public class ProductUpdated {
	
	private ProductService productService;
	
	@Autowired
	public ProductUpdated(ProductService productService) {
		this.productService = productService;
	}

	@RabbitListener(queues="${queue.update}")
	public void receive(@Payload Product product) {
		System.out.println("[+] Product Updated -> Updating Database...");
		System.out.println(product.toString());
		this.productService.updateProduct(product, product.getId());
	}
}