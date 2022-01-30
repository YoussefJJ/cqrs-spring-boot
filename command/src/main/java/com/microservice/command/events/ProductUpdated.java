package com.microservice.command.events;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.microservice.command.entities.Product;

@Component
public class ProductUpdated {
	private Queue productUpdatedQueue;
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public ProductUpdated(RabbitTemplate rabbitTemplate, Queue productUpdatedQueue) {
		this.rabbitTemplate = rabbitTemplate;
		this.productUpdatedQueue = productUpdatedQueue;
	}
	
	public void send(Product product) {
		this.rabbitTemplate.convertAndSend(this.productUpdatedQueue.getName(), product);
		System.out.println("[+] Event (Product Updated) emitted.");
	}

}