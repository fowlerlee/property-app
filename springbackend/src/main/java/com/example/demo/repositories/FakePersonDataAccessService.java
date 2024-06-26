package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Person;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
	List<Person> DB = new ArrayList<>();
	@Override
	public int insertPerson(UUID id, Person person) {
		DB.add(new Person(id, person.getFirstName(), person.getLastName(), person.getEmail(), person.getPassword(), person.getRole()));
		return 1;
	}
	
	public List<Person> selectAllPeople() {
		return DB;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		return DB.stream()
				.filter(person -> person.getId().equals(id)).
				findFirst();
	}

	@Override
	public int deletePersonById(UUID id) {
		 Optional<Person> person = selectPersonById(id);
		 if(person.isEmpty())
			 return 0;
		 
		 DB.remove(person.get());
		 return 1;
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		return selectPersonById(id)
			.map(p -> {
				int index = DB.indexOf(p);
				if (index >= 0) {
					DB.set(index, new Person(id, person.getFirstName(), person.getLastName(), person.getEmail(), person.getPassword(), person.getRole()));
					return 1;
				}
				return 0;
			}).orElse(0);
	}
	
}
