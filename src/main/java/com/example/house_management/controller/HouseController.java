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

import com.example.house_management.dto.request.HouseCreateRequestDTO;
import com.example.house_management.dto.request.HouseFilterRequestDTO;
import com.example.house_management.dto.request.HouseUpdateRequestDTO;
import com.example.house_management.dto.response.HouseResponseDTO;
import com.example.house_management.service.HouseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class HouseController {

	@Autowired
	private HouseService houseService;
	
	@ApiOperation(value = "Crate a new house", nickname = "createHouse")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "House is created")
	})
	@PostMapping("/houses")
	@ResponseStatus(value = HttpStatus.CREATED)
	public HouseResponseDTO createHouse(@Valid @RequestBody HouseCreateRequestDTO houseCreateRequestDTO) {
		return houseService.createHouse(houseCreateRequestDTO);
	}
	
	@ApiOperation(value = "Delete an existing house", nickname = "deleteHouse")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "House is deleted"),
			@ApiResponse(code = 404, message = "House not found")
	})
	@DeleteMapping("/houses/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteHouse(@PathVariable("id") Long id) {
		houseService.deleteHouse(id);
	}
	
	@ApiOperation(value = "Get the list of all houses", nickname = "getAllHouses")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of all houses is returned")
	})
	@GetMapping("/houses")
	@ResponseStatus(value = HttpStatus.OK)
	public List<HouseResponseDTO> getAllHouses(){
		return houseService.getAllHouses();
	}
	
	@ApiOperation(value = "Get the house by its id", nickname = "getHouseById")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "House is found"),
			@ApiResponse(code = 404, message = "House not found")
	})
	@GetMapping("/houses/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public HouseResponseDTO getHouseById(@PathVariable("id") Long id){
		return houseService.getHouseById(id);
	}
	
	@ApiOperation(value = "Get the list of all houses by city", nickname = "getAllHousesByCity")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of all houses found by city is returned")
	})
	@GetMapping("/houses-by-city")
	@ResponseStatus(value = HttpStatus.OK)
	public List<HouseResponseDTO> getAllHousesByCity(@RequestParam(required = true) String city){
		return houseService.getAllHousesByCity(city);
	}
	
	@ApiOperation(value = "Update the house by its id", nickname = "updateHouse")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "House is updated"),
			@ApiResponse(code = 404, message = "House not found")
	})
	@PutMapping("/houses/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public HouseResponseDTO updateHouse(@PathVariable("id") Long id, @Valid @RequestBody HouseUpdateRequestDTO houseUpdateRequestDTO){
		return houseService.updateHouse(id, houseUpdateRequestDTO);
	}
	
	@ApiOperation(value = "Get the list of all houses which contain country", nickname = "getAllHousesContainingCountry")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List of all houses containing country is returned")
	})
	@GetMapping("/houses-by-country")
	@ResponseStatus(value = HttpStatus.OK)
	public List<HouseResponseDTO> getAllHousesContainingCountry(@RequestParam(required = true) String country){
		return houseService.getAllHousesContainingCountry(country);
	}
	
	@ApiOperation(value = "Get the list with sorting and pagination of all houses based on filters", nickname = "getAllHouses")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sorted and Paginated List of all houses that match with filters is returned")
	})
	@PostMapping("/houses/filter")
	@ResponseStatus(value = HttpStatus.OK)
	public Page<HouseResponseDTO> getAllHouses(@Valid @RequestBody HouseFilterRequestDTO houseFilterRequestDTO) {
		return houseService.getAllHouses(houseFilterRequestDTO);
	}
	
}
