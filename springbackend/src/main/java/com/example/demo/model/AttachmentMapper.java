package com.example.demo.model;

import java.sql.ResultSet;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.util.UUID;

public class AttachmentMapper implements RowMapper<Attachment> {
	
	public Attachment mapRow(ResultSet resultSet, int i) throws SQLException {

		Attachment attachment = new Attachment();
		attachment.setId(UUID.fromString(resultSet.getString("id")));
		attachment.setFileName(resultSet.getString("filename"));
		attachment.setFileType(resultSet.getString("filetype"));
		attachment.setData(resultSet.getBytes("data"));
		return attachment;
	}
}
