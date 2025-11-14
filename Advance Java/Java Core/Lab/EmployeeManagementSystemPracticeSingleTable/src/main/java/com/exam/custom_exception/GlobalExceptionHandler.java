package com.exam.custom_exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exam.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), "failed"));
	}

	@ExceptionHandler(ResourseAlreadyExistException.class)
	public ResponseEntity<?> handleResourseAlreadyExistException(ResourseAlreadyExistException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), "already exist..."));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handelGeneralException(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse(e.getMessage(), "Exception occured"));
	}
}
