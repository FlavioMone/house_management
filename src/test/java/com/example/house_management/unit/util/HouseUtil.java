package com.example.house_management.unit.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.house_management.dto.HouseCreateRequestDTO;
import com.example.house_management.dto.HouseResponseDTO;
import com.example.house_management.dto.HouseUpdateRequestDTO;
import com.example.house_management.model.House;

public class HouseUtil {
	
	private HouseUtil() {
		
	}
	
	public static House getHouse() {
		House entity = new House();
		entity.setId(1L);
		entity.setAddress("test");
		entity.setCity("test");
		entity.setCountry("test");
		entity.setNumberOfBalconies(1);
		entity.setNumberOfBathrooms(1);
		entity.setNumberOfBedrooms(1);
		entity.setZipCode(1001);
		entity.setCreatedOn(LocalDateTime.now());
		entity.setModifiedAt(LocalDateTime.now());
		return entity;
	}
	
	public static HouseCreateRequestDTO getHouseCreateRequestDTO() {
		HouseCreateRequestDTO dto = new HouseCreateRequestDTO();
		dto.setAddress("test");
		dto.setCity("test");
		dto.setCountry("test");
		dto.setNumberOfBalconies(1);
		dto.setNumberOfBathrooms(1);
		dto.setNumberOfBedrooms(1);
		dto.setZipCode(1001);
		return dto;
	}
	
	public static HouseUpdateRequestDTO getHouseUpdateRequestDTO() {
		HouseUpdateRequestDTO dto = new HouseUpdateRequestDTO();
		dto.setNumberOfBalconies(1);
		dto.setNumberOfBathrooms(1);
		dto.setNumberOfBedrooms(1);
		return dto;
	}
	
	public static HouseResponseDTO getHouseResponseDTO() {
		HouseResponseDTO dto = new HouseResponseDTO();
		dto.setId(1L);
		dto.setAddress("test");
		dto.setCity("test");
		dto.setCountry("test");
		dto.setNumberOfBalconies(1);
		dto.setNumberOfBathrooms(1);
		dto.setNumberOfBedrooms(1);
		dto.setZipCode(1001);
		dto.setCreatedOn(LocalDateTime.now());
		dto.setModifiedAt(LocalDateTime.now());
		return dto;
	}
	
	public static List<House> getHouses(int size) {
		List<House> houses = new ArrayList<>();
		for(int i=0; i<size; i++) {
			houses.add(getHouse());
		}
		return houses;
	}
	
	public static List<HouseResponseDTO> getHouseResponseDTOs(int size) {
		List<HouseResponseDTO> houseResponseDTOs = new ArrayList<>();
		for(int i=0; i<size; i++) {
			houseResponseDTOs.add(getHouseResponseDTO());
		}
		return houseResponseDTOs;
	}

}
