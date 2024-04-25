package com.example.demo.model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Setter
@NoArgsConstructor
@Builder
public class Person implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	private UUID id;
	@NotBlank
	private String firstname;
	@NotBlank
	private String lastname;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public Person(@JsonProperty("id") UUID id, 
					@JsonProperty("firstname") String firstname,
					@JsonProperty("lastname") String lastname,
					@JsonProperty("email") String email,
					@JsonProperty("password") String password, 
					@JsonProperty("role") Role role) {
		
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public Person(@JsonProperty("id") UUID id, 	
			@JsonProperty("firstname") String firstname,
			@JsonProperty("lastname") String lastname, 
			@JsonProperty("email") String email,
			@JsonProperty("role") String role) {
		
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = Role.valueOf(role);
	}

	
	public UUID getId() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstname;
	}
	
	public String getLastName() {
		return this.lastname;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	// use this for the JWT token generation
	@Override
	public String getUsername() {
		return email;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = Role.valueOf(role);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
