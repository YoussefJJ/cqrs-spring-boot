package com.microservice.command.events;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservice.command.entities.Product;

@Component
public class ProductDeleted {
	private Queue productDeletedQueue;
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public ProductDeleted(RabbitTemplate rabbitTemplate, Queue productDeletedQueue) {
		this.rabbitTemplate = rabbitTemplate;
		this.productDeletedQueue = productDeletedQueue;
	}
	
	public void send(Product product) {
		this.rabbitTemplate.convertAndSend(this.productDeletedQueue.getName(), product);
		System.out.println("[+] Event (Product Deleted) emitted.");
	}

}

