package com.example.demo.authentification;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	
	private JwtService jwtService;
	private PersonRepository personRepository;
	
	public JwtAuthenticationFilter(JwtService jwtService,
									PersonRepository personRepository) {
		this.jwtService = jwtService;
		this.personRepository = personRepository;
		
	}

	@Override
	protected void doFilterInternal(
			@NotNull HttpServletRequest request, 
			@NotNull HttpServletResponse response, 
			@NotNull FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.split(" ")[1];
		
		userEmail = jwtService.extractUserName(jwt);
		
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			Person person = this.personRepository.findByEmail(userEmail).get();
			
			if(jwtService.isTokenValid(jwt, person)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						person.getUsername(),
						null,
						person.getAuthorities()
						);
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
 	}

}
