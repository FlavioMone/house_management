package com.example.house_management.exceptions;

public class CustomEntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -500712004443581489L;
	
	public CustomEntityNotFoundException(String message) {
		super(message);
	}
	
	public CustomEntityNotFoundException(Class<?> entityClass, Long id) {
		this(entityClass.getSimpleName() + " not found with id: " + id);
	}

}
