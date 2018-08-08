package com.ecobank.backend.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import com.ecobank.web.domain.frontend.FileRecordDTO;

public abstract class FileProcessor {

	public static final float VAT=1.05f;
	public abstract List<FileRecordDTO> read(InputStream inputStream, String excelFilePath) throws IOException;

	
}
