package com.projetorestapi.ErrorControllerAdvice;

import java.sql.SQLException;

import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ControllerAdvice
public class ControllerErrors extends ResponseEntityExceptionHandler{
	
	/*Tratamento da maioria dos erros*/
	@Override
	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String msg ="";
		
		if (ex instanceof MethodArgumentNotValidException) {
			List<org.springframework.validation.ObjectError> list = ((MethodArgumentNotValidException)ex).getBindingResult().getAllErrors();
			
			for (org.springframework.validation.ObjectError objectError : list) {
				msg += objectError.getDefaultMessage() +"\n";
			}
			
		}else {
			msg = ex.getMessage();
		}
		
		ObjectError objectError = new ObjectError();
		objectError.setError(ex.getMessage());
		objectError.setCode(status.value() + "==>" + status.getReasonPhrase() );
		
		return new ResponseEntity<>(objectError, headers, status);
		
	}
	
	/*Tratamento da marioria dos erros a nivel de banco de dados*/
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {
		
		String msg = "";
		if (ex instanceof ConstraintViolationException) {
			msg = ((ConstraintViolationException)ex).getCause().getCause().getMessage();
		}else if(ex instanceof DataIntegrityViolationException) {
			msg = ((DataIntegrityViolationException)ex).getCause().getCause().getMessage();
		}else if(ex instanceof SQLException) {
			msg = ((SQLException)ex).getCause().getCause().getMessage();
		}else {
			msg = ex.getMessage();
		}
		
		ObjectError objectError = new ObjectError();
		objectError.setError(msg);
		objectError.setCode(HttpStatus.INTERNAL_SERVER_ERROR + "==>" + HttpStatus.INTERNAL_SERVER_ERROR );
		
		return new ResponseEntity<>(objectError, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
