package com.example.house_management.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HouseCreateRequestDTO {
	
	@NotBlank(message = "'address' field cannot be empty")
	@JsonProperty("address")
	private String address;
	
	@NotNull(message = "'zipCode' field cannot be empty")
	@JsonProperty("zipCode")
	private Integer zipCode;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("country")
	private String country;
	
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
