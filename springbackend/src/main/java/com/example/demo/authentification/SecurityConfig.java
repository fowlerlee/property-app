package com.example.demo.authentification;


import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.model.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    
	JwtAuthenticationFilter jwtAuthenticationFilter;

	AuthenticationProvider authenticationProvider;
	
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
			AuthenticationProvider authenticationProvider) {
		
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.authenticationProvider = authenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
		
		http
				.csrf(csrfConfig -> csrfConfig.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> {
                	authConfig.requestMatchers(HttpMethod.GET, "/viewall").permitAll();
                	authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                	authConfig.requestMatchers(HttpMethod.POST, "/auth/register").permitAll();
                	authConfig.requestMatchers(HttpMethod.POST, "/api/v1/person").hasAuthority(Role.ADMIN.toString());
                	authConfig.requestMatchers(HttpMethod.GET, "/download").hasAuthority(Role.ADMIN.toString());
                	authConfig.requestMatchers(HttpMethod.POST, "/upload").hasAuthority(Role.ADMIN.toString());
                	authConfig.anyRequest().permitAll();
                });
        
	return http.build();
		
	}
}
