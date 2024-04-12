package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Person;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
	private final JdbcTemplate jdbcTemplate;
	
	public PersonDataAccessService(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public int insertPerson(UUID id, Person person) {
		String sql = "INSERT INTO person (id, name) VALUES (CAST(? AS UUID), CAST(? AS VARCHAR))";
		Object[] arguments = new Object[]{id.toString(), person.getName().toString()};
		jdbcTemplate.update(sql, arguments);
		return 1;
	}

	
	@Override
	public List<Person> selectAllPeople() {
		String sql = "SELECT id, name FROM person";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID id  = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			return new Person(id, name);
		});
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		final String sql = "SELECT id, name FROM person WHERE id = ?";
		Person person = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
			UUID personId = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			return new Person(personId, name);
		});
		return Optional.ofNullable(person);
			
	}

	@Override
	public int deletePersonById(UUID id) {
		String sql = "DELETE FROM person WHERE id = (CAST(? AS UUID))";
		Object[] arguments = new Object[]{id.toString()};
		jdbcTemplate.update(sql, arguments);
		return 1;
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		String sql = "UPDATE person SET id = (CAST(? AS UUID)), name = (CAST(? AS VARCHAR)) WHERE id = (CAST(? AS UUID))";
		Object[] arguments = new Object[]{id.toString(), person.getName().toString(), id.toString()};
		jdbcTemplate.update(sql, arguments);
		return 1;
	}

}
