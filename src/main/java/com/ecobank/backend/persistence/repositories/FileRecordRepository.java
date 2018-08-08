package com.ecobank.backend.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecobank.backend.persistence.domain.backend.FileRecord;
import com.ecobank.backend.persistence.domain.backend.FileUpload;

public interface FileRecordRepository extends JpaRepository<FileRecord, Long> {
	
	public List<FileRecord> findByFileUploadId(Long id);

}