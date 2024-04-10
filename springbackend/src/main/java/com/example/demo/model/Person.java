package com.example.demo.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class Person {
	
	private UUID id;
	@NotBlank
	private String name;
	
	public Person(@JsonProperty("id") UUID id, 
					@JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public UUID getId() {
		return this.id;
	}
}
