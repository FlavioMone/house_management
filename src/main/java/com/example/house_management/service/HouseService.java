package com.example.house_management.service;

import java.util.List;

import com.example.house_management.dto.HouseCreateRequestDTO;
import com.example.house_management.dto.HouseResponseDTO;
import com.example.house_management.dto.HouseUpdateRequestDTO;

public interface HouseService {
	
	HouseResponseDTO getHouseById(Long id);
	List<HouseResponseDTO> getAllHouses();
	List<HouseResponseDTO> getAllHousesByCity(String city);
	HouseResponseDTO createHouse(HouseCreateRequestDTO houseCreateRequestDTO);
	HouseResponseDTO updateHouse(Long id, HouseUpdateRequestDTO houseUpdateRequestDTO);
	void deleteHouse(Long id);
	public List<HouseResponseDTO> getAllHousesContainingCountry(String country);

}
