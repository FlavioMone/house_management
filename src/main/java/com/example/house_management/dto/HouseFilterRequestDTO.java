package com.example.house_management.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HouseFilterRequestDTO {

	@JsonProperty("cities")
	private List<String> cities;
	
	@JsonProperty("country")
	private String country;
	
	@Min(value = 1, message = "Minimum number of bedrooms should not be less than 1")
	@JsonProperty("minNumberOfBedrooms")
	private Integer minNumberOfBedrooms; 
	
	@JsonProperty("creationDateInterval")
	private Integer creationDateInterval;
	
	@Valid
	private PageDTO pageDTO;
	
}
