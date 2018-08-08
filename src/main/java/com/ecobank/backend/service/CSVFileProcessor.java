package com.ecobank.backend.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ecobank.enums.Direction;
import com.ecobank.web.controllers.FileController;
import com.ecobank.web.domain.frontend.CallLog;
import com.ecobank.web.domain.frontend.FileRecordDTO;

@Service
public class CSVFileProcessor {
	private static final Logger LOGGER = Logger.getLogger(CSVFileProcessor.class);

	public List<CallLog> read(InputStream inputStream, String excelFilePath) throws IOException {
		// Whick workbook are we working with. get it

		List<CallLog> fileRecordList = new ArrayList<>();

		CallLog callLog = null;
		CSVParser csvParser = new CSVParser(new InputStreamReader(inputStream), CSVFormat.DEFAULT);
		for (CSVRecord csvRecord : csvParser) {
			// Accessing Values by Column Index

			try { //something might go wrong
				String userid = csvRecord.get(0);
				String otherUserId = csvRecord.get(1);
				String direction = csvRecord.get(2);
				String length = csvRecord.get(3);
				String tstamp = csvRecord.get(4);

				// Create and initialise callLog Object
				callLog = new CallLog();

				callLog.setUserId(userid);
				callLog.setOtherUserId(otherUserId);
				callLog.setDirection(Direction.valueOf(direction.toUpperCase()));
				callLog.setLength(Integer.valueOf(length));
				callLog.setTimestamp(tstamp);
				fileRecordList.add(callLog);
				System.out.println(callLog);
			} catch (Exception e) {
				LOGGER.info("Something went wrong {}"+e.getMessage());
			}
		}
		return fileRecordList;
	}

	

	private int getCountValue(Object value) {

		try {
			if (value == null)
				return 0;
			String count = value.toString();
			if (count.endsWith(".0")) {
				count = count.substring(0, count.length() - 2);
			}

			return Integer.valueOf(count);
		} catch (Exception e) {
			return 0;
		}

	}

	private Double getChargesValue(Object value) {

		try {

			return Double.valueOf(value.toString());
		} catch (Exception e) {
			return 0.00d;
		}

	}

}
