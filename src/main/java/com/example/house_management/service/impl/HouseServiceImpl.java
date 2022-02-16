package com.example.house_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.house_management.dto.HouseCreateRequestDTO;
import com.example.house_management.dto.HouseFilterRequestDTO;
import com.example.house_management.dto.HouseResponseDTO;
import com.example.house_management.dto.HouseUpdateRequestDTO;
import com.example.house_management.exceptions.CustomEntityNotFoundException;
import com.example.house_management.model.House;
import com.example.house_management.repository.HouseRepository;
import com.example.house_management.service.HouseService;
import com.example.house_management.util.DateInterval;

@Service
public class HouseServiceImpl implements HouseService {
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private HouseRepository houseRepository;

	@Transactional
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

	@Override
	public Page<HouseResponseDTO> getAllHouses(HouseFilterRequestDTO houseFilterRequestDTO) {
		
		Sort sort = Sort.by(Sort.Direction.valueOf(houseFilterRequestDTO.getPageDTO().getSortingDirection()), houseFilterRequestDTO.getPageDTO().getSortingField());
		Pageable pageable = PageRequest.of(houseFilterRequestDTO.getPageDTO().getPageNumber() - 1, houseFilterRequestDTO.getPageDTO().getPageSize(), sort);
		
		return houseRepository.findAll(getSpecificationOfHouse(houseFilterRequestDTO), pageable)
							  .map(new Function<House, HouseResponseDTO>(){

								@Override
								public HouseResponseDTO apply(House entity) {
									return modelMapper.map(entity, HouseResponseDTO.class);
								}
							  });
	}

	private Specification<House> getSpecificationOfHouse(HouseFilterRequestDTO houseFilterRequestDTO) {
		Specification<House> houseSpecification = Specification.where(null);
		
		if(houseFilterRequestDTO.getCities() != null && !houseFilterRequestDTO.getCities().isEmpty()) {
			houseSpecification = houseSpecification.and(getCitySpecificationOfHouse(houseFilterRequestDTO.getCities()));
		}
		
		if(houseFilterRequestDTO.getCountry()!=null) {
			houseSpecification = houseSpecification.and(getCountrySpecificationOfHouse(houseFilterRequestDTO.getCountry()));
		}
		
		LocalDateTime filterDate = DateInterval.getFilterDateFromValue(houseFilterRequestDTO.getCreationDateInterval());
		if(filterDate != null) {
			houseSpecification = houseSpecification.and(getCreationDateIntervalSpecificationOfHouse(filterDate));
		}
		
		return houseSpecification;
	}

	private Specification<House> getCitySpecificationOfHouse(List<String> cities) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.and(root.get("city").in(cities));
	}
	
	private Specification<House> getCountrySpecificationOfHouse(String country) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("country"), country);
	}
	
	private Specification<House> getCreationDateIntervalSpecificationOfHouse(LocalDateTime filterDate) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("createdOn"), filterDate);
	}

	// AND, OR, EQUAL, GT, LT, GTE, LTE, BETWEEN, NOT, NOTEQUAL

}
