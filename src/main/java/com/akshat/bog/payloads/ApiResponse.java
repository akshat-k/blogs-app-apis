package com.akshat.bog.payloads;

import org.springframework.http.HttpStatus;

public class ApiResponse {

	private String message;
	private boolean success;
	private HttpStatus status_code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

		
	public HttpStatus getStatus_code() {
		return status_code;
	}

	public void setStatus_code(HttpStatus status_code) {
		this.status_code = status_code;
	}

	public ApiResponse(String message, boolean success, HttpStatus notFound) {
		super();
		this.message = message;
		this.success = success;
		this.status_code = notFound;
	}

	

	public ApiResponse() {
		// TODO Auto-generated constructor stub
	}

}
