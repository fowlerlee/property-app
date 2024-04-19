package com.example.demo.service;

import com.example.demo.model.Attachment;
import com.example.demo.dao.AttachmentDao;
import com.example.demo.fileupload.AttachmentResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    private AttachmentDao attachmentDao;

    public AttachmentServiceImpl(AttachmentDao dao) {
        this.attachmentDao = dao;
    }

    @Override
    public String saveAttachment(MultipartFile file) throws Exception {
       String fileName = StringUtils.cleanPath(file.getOriginalFilename());
       try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                + fileName);
            }

            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes());
            return attachmentDao.insertAttachment(attachment);
            

       } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
       }
    }
    
    @Override
    public List<AttachmentResponse> getAllAttachments() {
        return attachmentDao
                .selectAllAttachments();
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentDao
                .selectAttachmentById(UUID.fromString(fileId))
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }

	@Override
	public UUID deletedAttachmentById(UUID id) throws Exception {
		return attachmentDao.deleteAttachmentById(id);
	}
	
	@Override
	public Attachment updateAttachmentById(UUID id, MultipartFile file) throws Exception {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
       try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                + fileName);
            }

            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes());
            return attachmentDao.updateAttachmentById(id, attachment);

       } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
       }
	}
}
