package com.example.house_management.unit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.house_management.dto.HouseCreateRequestDTO;
import com.example.house_management.dto.HouseFilterRequestDTO;
import com.example.house_management.dto.HouseResponseDTO;
import com.example.house_management.exceptions.CustomEntityNotFoundException;
import com.example.house_management.model.House;
import com.example.house_management.repository.HouseRepository;
import com.example.house_management.service.HouseService;
import com.example.house_management.unit.util.HouseUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseServiceTest {
	
	@MockBean
	private HouseRepository houseRepository;
	
	@Autowired
	private HouseService houseService;
	
	@Test
	void givenHouse_whenCreated_thenReturnHouse() {
		House entity = HouseUtil.getHouse();
		HouseResponseDTO responseDTO = HouseUtil.getHouseResponseDTO();
		HouseCreateRequestDTO requestDTO = HouseUtil.getHouseCreateRequestDTO();
		
		Mockito.when(houseRepository.save(Mockito.any(House.class))).thenReturn(entity);
		
		HouseResponseDTO serviceResponse = houseService.createHouse(requestDTO);
		
		assertNotNull(serviceResponse);
		assertEquals(responseDTO.getId(), serviceResponse.getId());
		assertEquals(responseDTO.getAddress(), serviceResponse.getAddress());
		assertEquals(responseDTO.getCity(), serviceResponse.getCity());
		assertEquals(responseDTO.getCountry(), serviceResponse.getCountry());
		assertEquals(responseDTO.getNumberOfBalconies(), serviceResponse.getNumberOfBalconies());
		assertEquals(responseDTO.getNumberOfBathrooms(), serviceResponse.getNumberOfBathrooms());
		assertEquals(responseDTO.getNumberOfBedrooms(), serviceResponse.getNumberOfBedrooms());
	}
	
	@Test
	void givenNonExistingHouse_whenDeleted_thenThrowException() {
		
		Mockito.when(houseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		assertThrows(CustomEntityNotFoundException.class, () -> houseService.deleteHouse(1L));
	}
	
	@Test
	void givenExistingHouse_whenDeleted_thenOk() {
		House entity = HouseUtil.getHouse();
		
		Mockito.when(houseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
		Mockito.doNothing().when(houseRepository).delete(Mockito.any(House.class));
//		Mockito.doNothing().when(houseRepository).deleteById(Mockito.anyLong());
		
		houseService.deleteHouse(1L);
		Mockito.verify(houseRepository, Mockito.times(1)).delete(Mockito.any(House.class));
//		Mockito.verify(houseRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
	}
	
	@Test 
	void givenExistingHouse_whenRetrieved_thenReturn() {
		House entity = HouseUtil.getHouse();
		
		Mockito.when(houseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
		
		assertNotNull(houseService.getHouseById(1L));
	}
	
	@Test 
	void givenNonExistingHouse_whenRetrieved_thenThrowException() {
		
		Mockito.when(houseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		assertThrows(CustomEntityNotFoundException.class, () -> houseService.getHouseById(1L));
	}
	
	@Test
	void given_whenRetrieved_getAllHouses() {
		List<House> houses = HouseUtil.getHouses(3);
		
		Mockito.when(houseRepository.findAll()).thenReturn(houses);
		
		assertEquals(3, houseService.getAllHouses().size());
	}
	
	@Test
	void given_whenRetrieved_getAllHousesByCity() {
		List<House> houses = HouseUtil.getHouses(3);
		
		Mockito.when(houseRepository.getAllHousesByCity(Mockito.anyString())).thenReturn(houses);
		
		assertEquals(3, houseService.getAllHousesByCity("test").size());
	}
	
	@Test 
	void givenExistingHouse_whenUpdated_thenReturn() {
		House entity = HouseUtil.getHouse();
		
		Mockito.when(houseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
		Mockito.when(houseRepository.save(Mockito.any(House.class))).thenReturn(entity);
		
		assertNotNull(houseService.updateHouse(1L, HouseUtil.getHouseUpdateRequestDTO()));
	}
	
	@Test 
	void givenNonExistingHouse_whenUpdated_thenThrowException() {
		
		Mockito.when(houseRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		assertThrows(CustomEntityNotFoundException.class, () -> houseService.updateHouse(1L, HouseUtil.getHouseUpdateRequestDTO()));
	}
	
	@Test
	void given_whenRetrieved_getAllHousesContainingCountry() {
		List<House> houses = HouseUtil.getHouses(3);
		
		Mockito.when(houseRepository.getAllHousesContainingCountry(Mockito.anyString())).thenReturn(houses);
		
		assertEquals(3, houseService.getAllHousesContainingCountry("test").size());
	}
	
	@Test
	void givenFilter_whenRetrieved_getAllHouses() {
		Page<House> houses = HouseUtil.getPagesOfHouse(10, 1, 3, "ASC", "id");
		
		Mockito.when(houseRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class))).thenReturn(houses);
		
		assertEquals(3, houseService.getAllHouses(HouseUtil.getHouseFilterRequestDTO()).getNumberOfElements());
	}
	
	@Test
	void givenNullFilter_whenRetrieved_getAllHouses() {
		HouseFilterRequestDTO request = new HouseFilterRequestDTO();
		request.setPageDTO(HouseUtil.getPageDTO());
		Page<House> houses = HouseUtil.getPagesOfHouse(10, 1, 3, "ASC", "id");
		
		Mockito.when(houseRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class))).thenReturn(houses);
		
		assertEquals(3, houseService.getAllHouses(request).getNumberOfElements());
	}
	
	@Test
	void givenEmptyFilter_whenRetrieved_getAllHouses() {
		HouseFilterRequestDTO request = new HouseFilterRequestDTO();
		request.setCities(Arrays.asList());
		request.setPageDTO(HouseUtil.getPageDTO());
		Page<House> houses = HouseUtil.getPagesOfHouse(10, 1, 3, "ASC", "id");
		
		Mockito.when(houseRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class))).thenReturn(houses);
		
		assertEquals(3, houseService.getAllHouses(request).getNumberOfElements());
	}

}
