package com.ecobank.web.domain.frontend;

import java.util.Date;

import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FileUploadDTO {

	private long id;
	private String filename;
	private String mimeType;
	private String title;
	private Date createdAt;
	private String period;
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
