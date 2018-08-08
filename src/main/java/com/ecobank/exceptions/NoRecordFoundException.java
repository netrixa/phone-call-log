package com.ecobank.exceptions;


public class NoRecordFoundException extends Exception {

	private int errorCode=0;
    public NoRecordFoundException(Throwable e) {
        super(e);
    }
    public NoRecordFoundException(String message) {
        super(message);
    }
    public NoRecordFoundException(String message, int code) {
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
