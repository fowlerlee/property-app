//package com.example.demo.controller;
//
//import java.util.List;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.example.demo.authentification.SecurityConfig;
//import com.example.demo.model.Person;
//import com.example.demo.model.PersonMapper;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//
//@Controller
//public class SecurityController {
//	
//	SecurityConfig securityConfig;
//	
//	@Autowired
//	HttpSecurity httpSecurity;
//	
//	public SecurityController(SecurityConfig securityConfig) {
//		this.securityConfig = securityConfig;
//	}
//	
//	@GetMapping("/login/{name}/{password}")
//	public PersonMapper loginUser(@PathVariable("name") String name, @PathVariable("password") String password) {
//		try {
//			securityConfig.authManager(httpSecurity).authenticate((Authentication) new User(name, password, false, false, false, false, null));
//		} catch (AuthenticationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}
