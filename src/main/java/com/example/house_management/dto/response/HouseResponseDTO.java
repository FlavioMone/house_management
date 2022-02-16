package com.example.house_management.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HouseResponseDTO {
	
	private Long id;
	private String address;
	private Integer zipCode;
	private String city;
	private String country;
	private Integer numberOfBalconies;
	private Integer numberOfBedrooms;
	private Integer numberOfBathrooms;
	private LocalDateTime createdOn;
	private LocalDateTime modifiedAt;

}
