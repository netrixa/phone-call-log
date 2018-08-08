package com.ecobank.exceptions;


public class FileExistException extends RuntimeException {

	private int errorCode=0;
    public FileExistException(Throwable e) {
        super(e);
    }
    public FileExistException(String message) {
        super(message);
    }
    public FileExistException(String message, int code) {
        super(message);
        this.errorCode=code;
    }
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
