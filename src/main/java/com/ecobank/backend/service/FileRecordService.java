package com.ecobank.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecobank.backend.persistence.domain.backend.FileUpload;
import com.ecobank.backend.persistence.domain.backend.PhoneCallLog;
import com.ecobank.backend.persistence.repositories.PhoneCallLogRepository;
import com.ecobank.web.domain.frontend.CallLog;

@Service
public class FileRecordService {

	@Autowired
	private PhoneCallLogRepository phoneCallLogRepository;

	public List<PhoneCallLog> save(List<CallLog> listOfFileRecords, FileUpload fileUpload) {
		List<PhoneCallLog> entities = new ArrayList<>();
		for (CallLog fileRec : listOfFileRecords) {
			PhoneCallLog log=new PhoneCallLog();
			log.setDirection(fileRec.getDirection());
			log.setUserId(fileRec.getUserId());
			log.setOtherUserId(fileRec.getOtherUserId());
			log.setLength(fileRec.getLength());
			//log.setDuration(fileRec.getDuration());
			log.setTimestamp(fileRec.getTimestamp());
			log.setFileUpload(fileUpload);
			entities.add(log);
		}
		this.phoneCallLogRepository.save(entities);
		
		return entities;
	}
	
	public List<CallLog> fetchByFileUpload(long id) {
		List<CallLog> entities = new ArrayList<>();
		
		for (PhoneCallLog phoneCallLog : phoneCallLogRepository.findByFileUploadId(id)) {
			CallLog log=new CallLog();
			log.setDirection(phoneCallLog.getDirection());
			log.setUserId(phoneCallLog.getUserId());
			log.setOtherUserId(phoneCallLog.getOtherUserId());
			log.setLength(phoneCallLog.getLength());
			log.setId(phoneCallLog.getId());
			log.setTimestamp(phoneCallLog.getTimestamp());
			entities.add(log);
		}
		
		return entities;
	}
	
	
	
	
}
