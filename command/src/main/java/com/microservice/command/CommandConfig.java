package com.microservice.command;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {
	
	@Value("${queue.add}")
	private String productAddQueueName;
	@Value("${queue.update}")
	private String productUpdateQueueName;
	@Value("${queue.delete}")
	private String productDeleteQueueName;
	
	@Bean
	public ConnectionFactory connectionFactory() {
	    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
	    connectionFactory.setUri("amqps://hostname:password@roedeer.rmq.cloudamqp.com/hostname");
	    return connectionFactory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public Queue productAddedQueue() {
		return new Queue(productAddQueueName);
	}
	
	@Bean
	public Queue productUpdatedQueue() {
		return new Queue(productUpdateQueueName);
	}
	
	@Bean
	public Queue productDeletedQueue() {
		return new Queue(productDeleteQueueName);
	}
}
