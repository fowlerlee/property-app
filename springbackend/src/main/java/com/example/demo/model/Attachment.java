package com.example.demo.model;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
@NoArgsConstructor
@Data
@Setter
@Getter
public class Attachment {
	
	@Id
	@GeneratedValue
    private UUID id;
	
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    public Attachment(@JsonProperty("fileName")String fileName, @JsonProperty("fileType") String fileType, byte[] data) {
    	this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
    
    public Attachment(@JsonProperty("fileName")UUID id, @JsonProperty("fileName")String fileName, @JsonProperty("fileType") String fileType, byte[] data) {
    	this.id = id;
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
