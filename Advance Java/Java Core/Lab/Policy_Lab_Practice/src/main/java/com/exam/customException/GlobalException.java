package com.exam.customException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exam.dto.ApiResponse;

@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<?> handleResourseNotFoundException(ResourseNotFoundException e){
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ApiResponse(e.getMessage(), "Resourse not found"));
	}
	
	@ExceptionHandler(ResourseException.class)
	public ResponseEntity<?> handleResourseException(ResourseException e){
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ApiResponse(e.getMessage(),"Invalid Resourse"));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),"Error Occured"));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		List<FieldError> fieldError = e.getFieldErrors();
		Map<String,String> collect = fieldError.stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(collect);
	}
}
