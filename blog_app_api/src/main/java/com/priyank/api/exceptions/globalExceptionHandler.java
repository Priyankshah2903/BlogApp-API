package com.priyank.api.exceptions;
import com.priyank.api.payload.ApiResponse;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class globalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
   public  ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiresponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiresponse,HttpStatus.NOT_FOUND);
   }
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> respMap=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldNameString=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			respMap.put(fieldNameString, message);
		});
		return new ResponseEntity<Map<String, String>>(respMap,HttpStatus.BAD_REQUEST);
	}
}

