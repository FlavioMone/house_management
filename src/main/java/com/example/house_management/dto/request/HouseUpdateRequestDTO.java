package com.example.house_management.dto.request;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HouseUpdateRequestDTO {
	
	@Min(value = 0, message = "Number of balconies cannot be negative") 
	@JsonProperty("numberOfBalconies")
	private Integer numberOfBalconies;
	
	@Min(value = 1, message = "Number of bedrooms should not be less than 1")
	@JsonProperty("numberOfBedrooms")
	private Integer numberOfBedrooms;
	
	@Min(value = 1, message = "Number of bathrooms should not be less than 1")
	@JsonProperty("numberOfBathrooms")
	private Integer numberOfBathrooms;

}
