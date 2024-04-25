package com.example.demo.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.authentification.JwtService;
import com.example.demo.model.Person;

import com.example.demo.repositories.PersonRepository;
import com.example.demo.transferobjects.AuthenticationRequest;
import com.example.demo.transferobjects.AuthenticationResponse;
import com.example.demo.transferobjects.RegisterRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {
	
	private final PersonRepository personRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest request) {
		Person user = Person.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole())
				.build();
		personRepository.insertPerson(user);
		String jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        		request.getEmail(), request.getPassword());
        
        authenticationManager.authenticate(authToken);
        
		Person user = personRepository.findByEmail(request.getEmail()).orElseThrow();
		
		String jwtTokenString = jwtService.generateToken(user);
		
		return AuthenticationResponse
				.builder()
				.token(jwtTokenString)
				.build(); 
	}

}
