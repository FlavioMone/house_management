package com.example.house_management.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.house_management.dto.HouseCreateRequestDTO;
import com.example.house_management.dto.HouseFilterRequestDTO;
import com.example.house_management.dto.HouseResponseDTO;
import com.example.house_management.dto.HouseUpdateRequestDTO;
import com.example.house_management.service.HouseService;

@RestController
@RequestMapping("/api/v1")
public class HouseController {

	@Autowired
	private HouseService houseService;
	
	@PostMapping("/houses")
	@ResponseStatus(value = HttpStatus.CREATED)
	public HouseResponseDTO createHouse(@Valid @RequestBody HouseCreateRequestDTO houseCreateRequestDTO) {
		return houseService.createHouse(houseCreateRequestDTO);
	}
	
	@DeleteMapping("/houses/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteHouse(@PathVariable("id") Long id) {
		houseService.deleteHouse(id);
	}
	
	@GetMapping("/houses")
	@ResponseStatus(value = HttpStatus.OK)
	public List<HouseResponseDTO> getAllHouses(){
		return houseService.getAllHouses();
	}
	
	@GetMapping("/houses/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public HouseResponseDTO getHouseById(@PathVariable("id") Long id){
		return houseService.getHouseById(id);
	}
	
	@GetMapping("/houses-by-city")
	@ResponseStatus(value = HttpStatus.OK)
	public List<HouseResponseDTO> getAllHousesByCity(@RequestParam(required = true) String city){
		return houseService.getAllHousesByCity(city);
	}
	
	@PutMapping("/houses/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public HouseResponseDTO updateHouse(@PathVariable("id") Long id, @Valid @RequestBody HouseUpdateRequestDTO houseUpdateRequestDTO){
		return houseService.updateHouse(id, houseUpdateRequestDTO);
	}
	
	@GetMapping("/houses-by-country")
	@ResponseStatus(value = HttpStatus.OK)
	public List<HouseResponseDTO> getAllHousesContainingCountry(@RequestParam(required = true) String country){
		return houseService.getAllHousesContainingCountry(country);
	}
	
	@PostMapping("/houses/filter")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<HouseResponseDTO> getAllHouses(@Valid @RequestBody HouseFilterRequestDTO houseFilterRequestDTO) {
		return houseService.getAllHouses(houseFilterRequestDTO);
	}
	
}
