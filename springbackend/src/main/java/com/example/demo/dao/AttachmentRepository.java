package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.fileupload.AttachmentResponse;
import com.example.demo.model.Attachment;
import com.example.demo.model.AttachmentMapper;
import com.example.demo.model.AttachmentResponseMapper;



@Repository
public class AttachmentRepository implements AttachmentDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public AttachmentRepository(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@Override
	public String insertAttachment(UUID id, Attachment attachment) {
		String sql = "INSERT INTO attachment (id, filename, filetype, data) VALUES (? , ? , ? , ?)";
		Object[] arguments = new Object[]{id, attachment.getFileName(), attachment.getFileType(), attachment.getData()};
		jdbcTemplate.update(sql, arguments);
		return id.toString();
	}

	@Override
	public List<AttachmentResponse> selectAllAttachments() {
		String sql = "select * from attachment";
		return jdbcTemplate.query(sql, new AttachmentResponseMapper());
	}

	@Override
	public Optional<Attachment> selectAttachmentById(UUID id) {
		final String sql = "select * from attachment where id=?";
		Attachment  attachment = jdbcTemplate.queryForObject(sql, new AttachmentMapper(), new Object[] { id });
		return Optional.ofNullable(attachment);
	}
	
	@Override
	public UUID deleteAttachmentById(UUID id) {
		final String sql = "delete from attachment where id = ?";
		jdbcTemplate.update(sql, new Object[] {id});
		return id;
	}

	@Override
	public Attachment updateAttachmentById(UUID id, Attachment attachment) {
		String sql = "UPDATE attachment SET id = (?), filename = (?), filetype = (?), data = (?) WHERE id = (?)";
		Object[] arguments = new Object[]{id, attachment.getFileName(), attachment.getFileType(), attachment.getData(), id};
		jdbcTemplate.update(sql, arguments);
		return attachment;
	}

	

}
