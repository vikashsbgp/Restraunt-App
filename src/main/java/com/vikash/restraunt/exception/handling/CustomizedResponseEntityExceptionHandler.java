package com.vikash.restraunt.exception.handling;

import java.util.Date;

import com.vikash.restraunt.library.ExceptionResponseLibrary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@EnableWebMvc
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(RestrauntNotFoundException.class)
	public final ResponseEntity<ExceptionResponseLibrary> handleRestrauntNotFoundException(RestrauntNotFoundException ex, WebRequest request) {
//	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
//	        request.getDescription(false));
		ExceptionResponseLibrary exceptionResponseLibrary = new ExceptionResponseLibrary();
		exceptionResponseLibrary.setError(ex.getCause());
		exceptionResponseLibrary.setStatus(404);
		exceptionResponseLibrary.setMessage(ex.getMessage());
		exceptionResponseLibrary.setTimestamp(new Date());
		exceptionResponseLibrary.setData(null);
		exceptionResponseLibrary.setDetails(request.getDescription(true));
	    return new ResponseEntity<>(exceptionResponseLibrary, HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

}
