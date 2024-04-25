package com.example.demo.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.service.PersonService;

@Component
public class DataLoader implements CommandLineRunner {
	
	private PersonService personService;
	
	@Autowired
	private PersonRepository personDataAccessService;
	
	public DataLoader(PersonService personService) {
		this.personService = personService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
//		Person p1 = new Person();
//		p1.setFirstname("Joe");
//		p1.setLastname("Mack");
//		p1.setEmail("joe@gmail.com");
//		p1.setPassword("1234");
//		p1.setRole("PERSON");
//		personService.addPerson(p1);
		
//		Person p2 = new Person();
//		p2.setFirstname("Jossan");
//		p2.setLastname("Fowler");
//		p2.setEmail("jossan@gmail.com");
//		p2.setPassword("1234");
//		p2.setRole("PERSON");
//		personService.addPerson(p2);
//		
//		Person p3 = new Person();
//		p3.setFirstname("Lee");
//		p3.setLastname("Fowler");
//		p3.setEmail("fowler@gmail.com");
//		p3.setPassword("1234");
//		p3.setRole("PERSON");
//		personService.addPerson(p3);
//
//		Person p4 = new Person();
//		p4.setFirstname("Hilary");
//		p4.setLastname("Fowler");
//		p4.setEmail("hilary@gmail.com");
//		p4.setPassword("1234");
//		p4.setRole("PERSON");
//		personService.addPerson(p4);
		
//		boolean inputPasswordSameAsDatabase = personDataAccessService.isUserEmailPasswordInDatabase("joe@gmail.com", "1234");
//		System.out.println("password is correct: " + inputPasswordSameAsDatabase);
//		
//		boolean inputPasswordSameAsDatabase2 = personDataAccessService.isUserEmailPasswordInDatabase("fowler@gmail.com", "2222");
//		System.out.println("password is correct: " + inputPasswordSameAsDatabase2);
		
	}

}
