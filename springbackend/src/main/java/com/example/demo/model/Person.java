package com.example.demo.model;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Person {
	@Id
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
