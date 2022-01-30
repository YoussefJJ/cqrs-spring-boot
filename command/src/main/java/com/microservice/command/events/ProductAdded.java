package com.microservice.command.events;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservice.command.entities.Product;

@Component
public class ProductAdded {
	private Queue productAddedQueue;
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public ProductAdded(RabbitTemplate rabbitTemplate, Queue productAddedQueue) {
		this.productAddedQueue = productAddedQueue;
		this.rabbitTemplate = rabbitTemplate;
	}
	;
	
	public void send(Product product) {
		this.rabbitTemplate.convertAndSend(this.productAddedQueue.getName(), product);
		System.out.println("[+] Event (Product Added) emitted.");
	}

}
