package com.ecobank.backend.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecobank.backend.persistence.domain.backend.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
	FileUpload findByFilename(String filename);
}