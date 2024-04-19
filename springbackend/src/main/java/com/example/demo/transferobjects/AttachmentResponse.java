package com.example.demo.transferobjects;

import java.util.UUID;

import com.example.demo.model.Attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AttachmentResponse {
	private UUID id;
	private String fileName;
	private String fileType;
	private byte[] data;

	public AttachmentResponse(Attachment image) {
		setId(image.getId());
		setFileName(image.getFileName());
		setFileType(image.getFileType());
		setData(image.getData());
	}
}