package com.example.demo.service;

import com.example.demo.model.Attachment;
import com.example.demo.transferobjects.AttachmentResponse;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AttachmentService {
    String saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;
    
    List<AttachmentResponse> getAllAttachments() throws Exception;
    
    UUID deletedAttachmentById(UUID id) throws Exception;

	Attachment updateAttachmentById(UUID id, MultipartFile file) throws Exception;
}
