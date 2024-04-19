package com.example.demo.model;

import java.io.Serializable;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Data
@Setter
@Getter
public class Attachment implements Serializable{
	
	private static final long serialVersionUID = 1L;

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
    
}
