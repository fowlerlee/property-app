package com.example.demo.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

@Component
public class DataLoader implements CommandLineRunner {
	
	private PersonService personService;
	
	public DataLoader(PersonService personService) {
		this.personService = personService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		Person p1 = new Person();
		p1.setName("Joe");
		p1.setEmail("joe@gmail.com");
		p1.setPassword("1234");
		personService.addPerson(p1);
		
		Person p2 = new Person();
		p2.setName("Jossan");
		p2.setEmail("jossan@gmail.com");
		p2.setPassword("1234");
		personService.addPerson(p2);
		
		Person p3 = new Person();
		p3.setName("Lee Fowler");
		p3.setEmail("fowler@gmail.com");
		p3.setPassword("1234");
		personService.addPerson(p3);
		
		Person p4 = new Person();
		p4.setName("Hilary Fowler");
		p4.setEmail("hilary@gmail.com");
		p4.setPassword("1234");
		personService.addPerson(p4);
		
	}

}
