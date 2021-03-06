package com.example.house_management.aop;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.house_management.exceptions.CustomEntityNotFoundException;
import com.example.house_management.exceptions.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandling {
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleNullPointerExceptions(NullPointerException ex) {
		log.error("Unhandled NullPointerException", ex);
		
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				"There was an unexpected null value.", true);
	}
	
	@ExceptionHandler(CustomEntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponse handleCustomEntityNotFoundException(CustomEntityNotFoundException ex) {
		log.error("Custom entity not found exception", ex);
		
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), false);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
		log.error("Entity not found exception", ex);
		
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), true);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleGenericExceptions(Exception ex) {
		log.error("Unhandled generic exception", ex);
		
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				"There was an unexpected situation. Please contact the page administrator.", true);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleDataIntegrityViolationExceptions(DataIntegrityViolationException ex) {
		log.error("There was an integrity problem while data is being saved in database", ex);
		
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), 
				"There was a problem with the integrity of data while being saved in database. Please check that the fields have correct values", false);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error("There was an integrity problem while validating arguments", ex);
		
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage(), false);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		log.error("There was an integrity problem with argument type", ex);
		
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getName() + " should be of type " + ex.getRequiredType().getName(), false);
	}
	
	@ExceptionHandler(PropertyReferenceException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handlePropertyReferenceException(PropertyReferenceException ex) {
		log.error("Property not found", ex);
		
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), false);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		log.error("JSON not in proper format", ex);
		
		return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "There is a problem while trying to read the JSON object. It is not in the proper format", false);
	}

}
