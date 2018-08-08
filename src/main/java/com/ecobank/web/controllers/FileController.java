package com.ecobank.web.controllers;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ecobank.backend.persistence.domain.backend.FileRecord;
import com.ecobank.backend.persistence.domain.backend.FileUpload;
import com.ecobank.backend.persistence.domain.backend.PhoneCallLog;
import com.ecobank.backend.persistence.repositories.PhoneCallLogRepository;
import com.ecobank.backend.service.CSVFileProcessor;
import com.ecobank.backend.service.FileRecordService;
import com.ecobank.backend.service.FileUploadService;
import com.ecobank.exceptions.FileExistException;
import com.ecobank.web.domain.frontend.CallLog;
import com.ecobank.web.domain.frontend.FileRecordDTO;
import com.ecobank.web.domain.frontend.FileUploadDTO;



@Controller
@RequestMapping(value = "/file")
public class FileController {

	private static final Logger LOGGER = Logger.getLogger(FileController.class);
	@Autowired
	FileUploadService fileUploadService;
	@Autowired
	private FileRecordService fileRecordService;
	@Autowired
	private CSVFileProcessor fileProcessor;
	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private PhoneCallLogRepository phoneCallLogRepository;
	@Autowired
	HttpServletRequest request;
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String showFileUploadForm() {
		return "admin/upload-log";
	}

	@RequestMapping(value = "/uploaded/files", method = RequestMethod.GET)
	public String listUploadedFiles(HttpServletRequest request) {
		return "admin/uploads";

	}
	
	@RequestMapping(value = "/uploaded/file/{id}/records", method = RequestMethod.GET)
	public ModelAndView showUploadedFileRecord(@PathVariable("id") String uploadedFileId) {
		ModelAndView map=new ModelAndView("admin/upload");
		FileUpload fileUpload=this.fileUploadService.findById(Long.valueOf(uploadedFileId));
		map.addObject("fileUpload", fileUpload);
		boolean canDelete=request.isUserInRole("ROLE_DPO");
		map.addObject("show", canDelete);
		map.setViewName("admin/uploaded-call-logs");
		return map;

	}
	
	@ResponseBody
	@RequestMapping(value = "/uploaded/file/{id}/records", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,List<CallLog>> _showCallLog(@PathVariable("id") String uploadedFileId) {
		
		List<CallLog>  callLogs = new ArrayList<>();
		callLogs=this.fileRecordService.fetchByFileUpload(Long.parseLong(uploadedFileId));
		Map<String,List<CallLog>> responseMap=new HashMap<>();
		responseMap.put("data",callLogs);
		return responseMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/call-log/delete", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,String> removeRecord(HttpServletRequest request) {
		
		Map<String,String> response=new HashMap<>();
		try {
			
			System.out.println("=============================================");
			String id=request.getParameter("id");
			System.out.println("=============================================");

			System.out.println(id);
			System.out.println("***********************************************");

			if(id==null) {
				throw new Exception("Record Not Found");
			}
			PhoneCallLog phoneCallLog=this.phoneCallLogRepository.findById(Long.valueOf(id));

			if(null==phoneCallLog) {
				throw new Exception("Record not found!");
			}
			this.phoneCallLogRepository.delete(phoneCallLog);
		
			LOGGER.info("File deleted");
			response.put("message", "Recors removed successfully");
			response.put("status", "00");

		}catch(Exception e) {
			response.put("status", "01");
			response.put("message",  e.getMessage());

		}
		return response;

	}

	@ResponseBody
	@RequestMapping(value = "/uploaded/files", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,List<FileUploadDTO>> uploadedFiles(HttpServletRequest request) {

		List<FileUploadDTO> fileUploadDTOs = new ArrayList<>();
		FileUploadDTO fileUploadDTO = null;

		for (FileUpload up : this.fileUploadService.findAll()) {
			fileUploadDTO = new FileUploadDTO();
			fileUploadDTO.setId(up.getId());
			fileUploadDTO.setFilename(up.getFilename());
			fileUploadDTO.setTitle(up.getTitle());
			fileUploadDTO.setCreatedAt(up.getCreatedAt());
			fileUploadDTOs.add(fileUploadDTO);
		}
		
		
		
		Map<String,List<FileUploadDTO>> responseMap=new HashMap<>();
		responseMap.put("data",fileUploadDTOs);
		return responseMap;
	}

	
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map uploadFile(MultipartHttpServletRequest request) {

		Map<String, String> responseMap = null;
		try {

			// Collect params
			responseMap = new HashMap<>();

			String title = request.getParameter("title");
			
			if (title == null || title.isEmpty()) {
				throw new Exception("Enter upload title");
			}
			

			Enumeration<String> reqs = request.getParameterNames();
			Iterator<String> itr = request.getFileNames();

			while (itr.hasNext()) {
				String uploadedFile = itr.next();
				MultipartFile file = request.getFile(uploadedFile);
				String mimeType = file.getContentType();
				String filename = file.getOriginalFilename();
			
				byte[] bytes = file.getBytes();

				FileUpload fileUpload = new FileUpload();
				fileUpload.setFilename(filename);
				fileUpload.setMimeType(mimeType);
				fileUpload.setTitle(title);
				
				
				
				if (fileUploadService.fileExists(fileUpload)) {
					//throw new FileExistException(ctx.getMessage("upload.file.exist", null, null), 1000);
				}
				fileUpload = fileUploadService.uploadFile(fileUpload);

				List<CallLog> fileRecordList = this.fileProcessor.read(file.getInputStream(), filename);
				this.fileRecordService.save(fileRecordList, fileUpload);
				responseMap.put("status", String.valueOf(HttpStatus.OK.value()));
				responseMap.put("message", ctx.getMessage("upload.file.ok", null, null));
			}
		} catch (FileExistException e) {
			responseMap.put("status", String.valueOf(e.getErrorCode()));
			responseMap.put("message", e.getMessage());
		} catch (Exception e) {
			responseMap.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
			responseMap.put("message", e.getMessage());
		}
		LOGGER.info(responseMap.toString());
		return responseMap;
	}
	
	
	

	

}
