package com.ecobank.backend.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecobank.backend.persistence.domain.backend.FileRecord;
import com.ecobank.backend.persistence.domain.backend.PhoneCallLog;

public interface PhoneCallLogRepository extends CrudRepository<PhoneCallLog, Integer> {

	public List<PhoneCallLog> findByFileUploadId(Long id);
	public PhoneCallLog findById(Long id);
}
