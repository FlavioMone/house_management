package com.example.house_management.dto;

import java.util.List;

import javax.validation.Valid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HouseFilterRequestDTO {

	private List<String> cities;
	private String country;
	private Integer creationDateInterval;
	
	@Valid
	private PageDTO pageDTO;
	
}
