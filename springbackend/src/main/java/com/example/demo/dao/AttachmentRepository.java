package com.example.demo.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Attachment;

@Repository
public class AttachmentRepository implements AttachmentDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public AttachmentRepository(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public String insertAttachment(UUID id, Attachment attachment) {
		String sql = "INSERT INTO attachment (id, filename, filetype, data) VALUES (CAST(? AS UUID), CAST(? AS VARCHAR), CAST(? AS VARCHAR), CAST(? AS BYTEA))";
		Object[] arguments = new Object[]{id.toString(), 
											attachment.getFileName().toString(),
											attachment.getFileType().toString(),
											attachment.getData().toString()};
		jdbcTemplate.update(sql, arguments);
		return id.toString();
	}
//
//	@Override
//	public List<Attachment> selectAllAttachments() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
	@Override
	public Optional<Attachment> selectAttachmentById(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

//	@Override
//	public int deleteAttachmentById(UUID id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int updateAttachmentById(UUID id, Attachment Attachment) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	

}
