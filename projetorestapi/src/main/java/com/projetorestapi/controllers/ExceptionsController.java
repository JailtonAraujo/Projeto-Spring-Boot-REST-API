package com.projetorestapi.controllers;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.projetorestapi.service.ErrorsObjects;


public class ExceptionsController extends ResponseEntityExceptionHandler{

	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
			String msg ="";
			
			if(ex instanceof MethodArgumentNotValidException) {
				
				List<ObjectError> listErros = ( (MethodArgumentNotValidException)ex ).getBindingResult().getAllErrors();
					
				for (ObjectError objectError : listErros) {
					msg += objectError.getDefaultMessage() + "\n";
				}
				
			}else {
				msg = ex.getMessage();
			}
			
			ErrorsObjects errorsObjects = new ErrorsObjects();
			errorsObjects.setError(msg);
			errorsObjects.setCode(status.value()+ " ==> " + status.getReasonPhrase());
		
		return new ResponseEntity<>(errorsObjects, headers, status);
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex){
		
		String msg = "";
		
		if(ex instanceof DataIntegrityViolationException) {
			msg = ((DataIntegrityViolationException)ex).getCause().getCause().getMessage();
		}
		else if(ex instanceof ConstraintViolationException) {
			msg = ((ConstraintViolationException)ex).getCause().getCause().getMessage();
		}
		else if(ex instanceof SQLException) {
			msg = ((ConstraintViolationException)ex).getCause().getCause().getMessage();
		}else{
			msg = ex.getMessage();
		}
		
		ErrorsObjects errorsObjects = new ErrorsObjects();
		errorsObjects.setError(msg);
		errorsObjects.setCode(HttpStatus.INTERNAL_SERVER_ERROR + " ==>" + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		
		return new ResponseEntity<>(errorsObjects, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
