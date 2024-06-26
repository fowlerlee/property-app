package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.lock.RedisLock;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonDao;

@Service
public class PersonService {
	private final PersonDao personDao;
	

	public PersonService(@Qualifier("postgres") PersonDao personDao){
		this.personDao = personDao;
	}
	
	@RedisLock(key = "myLockKey", timeout = 10, timeUnit = TimeUnit.SECONDS)
	public int addPerson(Person person) {
		return personDao.insertPerson(person);
	}
	
	public List<Person> getAllPeople() {
		return personDao.selectAllPeople();
	}
	
	public Optional<Person> getPersonById(UUID id) { 
		return personDao.selectPersonById(id);
	}
	
	@RedisLock(key = "myLockKey", timeout = 10, timeUnit = TimeUnit.SECONDS)
	public int deletePerson(UUID id) {
		return personDao.deletePersonById(id);
	}
	
	@RedisLock(key = "myLockKey", timeout = 10, timeUnit = TimeUnit.SECONDS)
	public int updatePerson(UUID id, Person person) {
		return personDao.updatePersonById(id, person);
	}

}
