package com.example.demo.model;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@NoArgsConstructor
public class Person {
	@Id
	private UUID id;
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	
	public Person(@JsonProperty("id") UUID id, 
					@JsonProperty("name") String name,
					@JsonProperty("email") String email,
					@JsonProperty("password") String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name, @JsonProperty("email") String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return this.name;
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassWord() {
		return this.password;
	}

}
