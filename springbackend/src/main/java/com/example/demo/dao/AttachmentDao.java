package com.example.demo.dao;

import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Attachment;


public interface AttachmentDao {
	String insertAttachment(UUID id, Attachment Attachment);
	
	default String insertAttachment(Attachment Attachment) {
		UUID id = UUID.randomUUID();
		return insertAttachment(id, Attachment);
	}

//	List<Attachment> selectAllAttachments();
//	
	Optional<Attachment> selectAttachmentById(UUID id);
//	
//	int deleteAttachmentById(UUID id);
//	
//	int updateAttachmentById(UUID id, Attachment Attachment);

}
