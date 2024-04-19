package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Person;
import com.example.demo.model.PersonMapper;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {
	private final JdbcTemplate jdbcTemplate;
	
	public PersonDataAccessService(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public int insertPerson(UUID id, Person person) {
		String sql = "INSERT INTO person (id, name, email, password) VALUES ( ? , ? , ? , crypt( ? , gen_salt('bf')) )";
		Object[] arguments = new Object[]{id, person.getName(), person.getEmail(), person.getPassWord()};
		jdbcTemplate.update(sql, arguments);
		return 1;
	}

	@Override
	public List<Person> selectAllPeople() {
		String sql = "SELECT id, name, email FROM person";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID id  = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			
			return new Person(id, name, email);
		});
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		final String sql = "select * from person where id=?";
		Person  person = jdbcTemplate.queryForObject(sql, new PersonMapper(), new Object[] { id });
		return Optional.ofNullable(person);
	}

	@Override
	public int deletePersonById(UUID id) {
		String sql = "DELETE FROM person WHERE id = (?)";
		Object[] arguments = new Object[]{id};
		jdbcTemplate.update(sql, arguments);
		return 1;
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		String sql = "UPDATE person SET id = (?), name = (?), email = (?), password = (?) WHERE id = (?)";
		Object[] arguments = new Object[]{id, person.getName(), person.getEmail(), person.getPassWord(), id};
		jdbcTemplate.update(sql, arguments);
		return 1;
	}

}
