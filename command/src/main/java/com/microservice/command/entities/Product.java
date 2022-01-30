package com.microservice.command.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="products")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product implements Serializable{
	
	@Id
	private String id = "";
	
	private String name = "";
	
	private double price = 0.0;
	
	private int quantity = 0;
	
	@Override
	public String toString() {
		return String.format("Product[id='%s', name='%s', price='%f', quantity='%d']", id, name, price, quantity);
	}
}
