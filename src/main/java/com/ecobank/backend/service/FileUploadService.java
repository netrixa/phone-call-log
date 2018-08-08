package com.ecobank.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecobank.backend.persistence.domain.backend.FileRecord;
import com.ecobank.backend.persistence.domain.backend.FileUpload;
import com.ecobank.backend.persistence.repositories.FileUploadRepository;

@Service
public class FileUploadService {

	@Autowired
	FileUploadRepository fileUploadRepository;

	// Retrieve file
	public FileUpload findByFilename(String filename) {
		return fileUploadRepository.findByFilename(filename);
	}
	public FileUpload findById(Long id) {
		return fileUploadRepository.findOne(id);
	}
	public List<FileUpload> findAll() {
		 return fileUploadRepository.findAll();
	}
	public void delete(Long id) {
		  fileUploadRepository.delete(id);
	}
	

	// Upload the file
	public FileUpload uploadFile(FileUpload doc) {
		return fileUploadRepository.saveAndFlush(doc);
	}

	public boolean fileExists(FileUpload doc) {
		if (null != fileUploadRepository.findByFilename(doc.getFilename()))
			return true;
		else
			return false;
	}

}
