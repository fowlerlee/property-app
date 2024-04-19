package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.model.Attachment;
import com.example.demo.transferobjects.AttachmentResponse;


public interface AttachmentDao {
	String insertAttachment(UUID id, Attachment Attachment);
	
	default String insertAttachment(Attachment Attachment) {
		UUID id = UUID.randomUUID();
		return insertAttachment(id, Attachment);
	}

	List<AttachmentResponse> selectAllAttachments();
	
	Optional<Attachment> selectAttachmentById(UUID id);
	
	UUID deleteAttachmentById(UUID id);

	Attachment updateAttachmentById(UUID id, Attachment Attachment);
	
}
