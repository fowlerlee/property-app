package com.example.demo.api;

import com.example.demo.fileupload.ResponseData;
import com.example.demo.service.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.extern.slf4j.Slf4j;
import com.example.demo.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@Slf4j
public class AttachmentController {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        String attachmentId = null;
        String downloadURl = "";
        attachmentId = attachmentService.saveAttachment(file);
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachmentId)
                .toUriString();

        return new ResponseData(file.getName(),
                downloadURl,
                file.getContentType(),
                file.getSize());
    }
    
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UUID> deletedAttachmentById(@PathVariable("id") UUID id) {
		UUID uuid = null;
		try {
			uuid = attachmentService.deletedAttachmentById(id);
		} catch (Exception e) {
			log.info("Attachment not deleted: id: ", id.toString());
		}
		return ResponseEntity.ok()
				.body(uuid);
	}
	
	@GetMapping("/viewall")
	@JsonIgnore
	public ResponseEntity<Object> getAllAttachments() throws Exception{
		List<Attachment> list = null;
		list = attachmentService.getAllAttachments();
		
	    List<Attachment> entities = new ArrayList<Attachment>();
	    for (Attachment n : list) {
	        Attachment attachment = new Attachment();
	        attachment.setId(n.getId());
	        attachment.setFileName(n.getFileName());
	        attachment.setFileType(n.getFileType());
	        attachment.setData(n.getData());
	        entities.add(attachment);
	    }
	    return new ResponseEntity<Object>(entities, HttpStatus.OK);
	}

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
    
    @PutMapping("update/{fileId}") 
    public ResponseEntity<Resource> updateFile(@PathVariable String fileId, @RequestParam("file") MultipartFile file) throws Exception {
    	Attachment attachment = null;
    	attachment = attachmentService.updateAttachmentById(UUID.fromString(fileId), file);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(attachment.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                + "\"")
				.body(new ByteArrayResource(attachment.getData()));
    }
}
