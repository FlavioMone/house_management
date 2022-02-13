package com.example.house_management.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = -4750250116347634634L;
	
	private int status;
	private String message;
	private boolean internal;
	
	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
		this.internal = true;
	}

}
