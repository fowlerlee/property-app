package com.example.demo.model;


import java.sql.ResultSet;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.util.UUID;

public class PersonMapper implements RowMapper<Person> {
	
	public Person mapRow(ResultSet resultSet, int i) throws SQLException {

		Person person = new Person();
		person.setId(UUID.fromString(resultSet.getString("id")));
		person.setName(resultSet.getString("name"));
		return person;
	}

}