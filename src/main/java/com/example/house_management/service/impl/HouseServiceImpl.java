package com.example.house_management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.house_management.dto.HouseCreateRequestDTO;
import com.example.house_management.dto.HouseResponseDTO;
import com.example.house_management.dto.HouseUpdateRequestDTO;
import com.example.house_management.exceptions.CustomEntityNotFoundException;
import com.example.house_management.model.House;
import com.example.house_management.repository.HouseRepository;
import com.example.house_management.service.HouseService;

@Service
public class HouseServiceImpl implements HouseService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private HouseRepository houseRepository;

	@Override
	public HouseResponseDTO createHouse(HouseCreateRequestDTO houseCreateRequestDTO) {
		House entity = modelMapper.map(houseCreateRequestDTO, House.class);
		entity = houseRepository.save(entity);
		return modelMapper.map(entity, HouseResponseDTO.class);
	}

	@Transactional
	@Override
	public void deleteHouse(Long id) {
		// Way 1
		House entity = houseRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException(House.class, id));
		houseRepository.delete(entity);
		
		// Way 2
//		if(!houseRepository.findById(id).isPresent()) {
//			throw new CustomEntityNotFoundException(House.class, id);
//		}
//		houseRepository.deleteById(id);
	}

	@Override
	public HouseResponseDTO getHouseById(Long id) {
		House entity = houseRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException(House.class, id));
		return modelMapper.map(entity, HouseResponseDTO.class);
	}

	@Override
	public List<HouseResponseDTO> getAllHouses() {
		return houseRepository.findAll().stream().map(house -> modelMapper.map(house, HouseResponseDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<HouseResponseDTO> getAllHousesByCity(String city) {
		List<House> houses = houseRepository.getAllHousesByCity(city);
		return houses.stream().map(house -> modelMapper.map(house, HouseResponseDTO.class)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public HouseResponseDTO updateHouse(Long id, HouseUpdateRequestDTO houseUpdateRequestDTO) {
		House entity = houseRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException(House.class, id));
		Optional.ofNullable(houseUpdateRequestDTO.getNumberOfBalconies())
				.ifPresent(entity::setNumberOfBalconies);
		Optional.ofNullable(houseUpdateRequestDTO.getNumberOfBathrooms())
				.ifPresent(entity::setNumberOfBathrooms);
		Optional.ofNullable(houseUpdateRequestDTO.getNumberOfBedrooms())
				.ifPresent(entity::setNumberOfBedrooms);
		entity = houseRepository.save(entity);
		return modelMapper.map(entity, HouseResponseDTO.class);
	}

	@Override
	public List<HouseResponseDTO> getAllHousesContainingCountry(String country) {
		List<House> houses = houseRepository.getAllHousesContainingCountry(country.toLowerCase());
		return houses.stream().map(house -> modelMapper.map(house, HouseResponseDTO.class)).collect(Collectors.toList());
	}

}
