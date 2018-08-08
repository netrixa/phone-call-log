package com.ecobank.backend.persistence.domain.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.UniqueConstraint;


import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CRBT_FILE_UPLOAD")
public class FileUpload extends BaseEntity {

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<FileRecord> getFileRecords() {
		return fileRecords;
	}

	public void setFileRecords(List<FileRecord> fileRecords) {
		this.fileRecords = fileRecords;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String filename;
	@Lob
	private String mimeType;
	private String title;
	@Enumerated(value=EnumType.STRING)
    @OneToMany(mappedBy = "fileUpload", fetch = FetchType.EAGER)
	private List<FileRecord> fileRecords=new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	private String period;

	public FileUpload(String filename, String mimeType) {
		this.filename = filename;
		this.mimeType = mimeType;
	}

	public FileUpload() {
		// Default Constructor
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

	
	
	
}
