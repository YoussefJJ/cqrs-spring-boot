package com.microservice.query.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName="product")
public class Product implements Serializable{

	@Id
	private String id;
	
	private String name;
	
	private double price;
	
	private int quantity;
	
	@Override
	public String toString() {
		return String.format("Product[id='%s', name='%s', price='%f', quantity='%d']", id, name, price, quantity);
	}
}
