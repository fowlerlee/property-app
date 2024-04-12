package com.example.demo.model;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
public class Attachment {
	
	@Id
    private UUID id;
	
    private String fileName;
    private String fileType;

    private byte[] data;

    public Attachment(@JsonProperty("fileName")String fileName, @JsonProperty("fileType") String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

	public UUID getId() {
		return id;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public byte[] getData() {
		return data;
	}
    
}
