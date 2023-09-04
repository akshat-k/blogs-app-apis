package com.akshat.bog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.akshat.bog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false, HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		System.out.println("Inside Exceptions..................................----------------->");
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError) error).getField();
			String messages = error.getDefaultMessage();
			errors.put(field, messages);

		});

		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<ApiResponse> handleAPIException(APIException ex) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, true, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);

	}
	
}
