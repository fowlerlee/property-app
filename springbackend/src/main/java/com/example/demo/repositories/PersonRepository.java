package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Person;
import com.example.demo.model.PersonMapper;

@Repository("postgres")
public class PersonRepository implements PersonDao {
	private final JdbcTemplate jdbcTemplate;
	
	public PersonRepository(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public int insertPerson(UUID id, Person person) {
//		String sql = "INSERT INTO person (id, firstname, lastname, email, password, role) VALUES ( ? , ? , ? , ? , crypt( ? , gen_salt('bf')) , ? )";
		String sql = "INSERT INTO person (id, firstname, lastname, email, password, role) VALUES ( ? , ? , ? , ? , ? , ? )";
		Object[] arguments = new Object[]{id, person.getFirstName(), person.getLastName(), person.getEmail(), person.getPassword(), person.getRole().toString()};
		jdbcTemplate.update(sql, arguments);
		return 1;
	}

	@Override
	public List<Person> selectAllPeople() {
		String sql = "SELECT id, firstname, lastname, email, role FROM person";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID id  = UUID.fromString(resultSet.getString("id"));
			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			String email = resultSet.getString("email");
			String role = resultSet.getString("role");
			
			return new Person(id, firstname, lastname, email, role);
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
		String sql = "UPDATE person SET id = (?), firstname = (?), lastname = (?), email = (?), password = (?), role = (?) WHERE id = (?)";
		Object[] arguments = new Object[]{id, person.getFirstName(), person.getLastName(), person.getEmail(), person.getPassword(), person.getRole(), id};
		jdbcTemplate.update(sql, arguments);
		return 1;
	}
	
	public boolean isUserEmailPasswordInDatabase(String email, String password) {
		String sql = "select * from person where email = (?) and password = crypt( (?) , password)";
		List<Person> query = jdbcTemplate.query(sql, new PersonMapper(), new Object[] { email, password });
		if (query.isEmpty()) {
			return false;
		}
		return true;
	}

	public  Optional<Person> findByEmail(String email) {
		final String sql = "select * from person where email = ( ? )";
		Person  person = jdbcTemplate.queryForObject(sql, new PersonMapper(), new Object[] { email });
		return Optional.ofNullable(person);
	}

}
