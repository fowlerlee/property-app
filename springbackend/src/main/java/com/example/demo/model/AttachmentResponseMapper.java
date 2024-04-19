package com.example.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;

import com.example.demo.transferobjects.AttachmentResponse;


public class AttachmentResponseMapper implements RowMapper<AttachmentResponse> {
	
	public AttachmentResponse mapRow(ResultSet resultSet, int i) throws SQLException {

		AttachmentResponse response = new AttachmentResponse();
		response.setId(UUID.fromString(resultSet.getString("id")));
		response.setFileName(resultSet.getString("filename"));
		response.setFileType(resultSet.getString("filetype"));
		response.setData(resultSet.getBytes("data"));
		return response;
	}

}
