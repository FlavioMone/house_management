package com.example.house_management.dto.request;

import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageDTO {
	
	@Min(value = 1, message = "Page size cannot be less than 1")
	private Integer pageSize;
	@Min(value = 1, message = "Page number cannot be less than 1")
	private Integer pageNumber;
	private String sortingField;
	private String sortingDirection;

}
