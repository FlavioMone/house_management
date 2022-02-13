package com.example.house_management.unit.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.house_management.controller.HouseController;
import com.example.house_management.dto.HouseCreateRequestDTO;
import com.example.house_management.dto.HouseResponseDTO;
import com.example.house_management.dto.HouseUpdateRequestDTO;
import com.example.house_management.service.HouseService;
import com.example.house_management.unit.util.HouseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseControllerTest {
	
	@MockBean
	private HouseService houseService;
	
	@InjectMocks
	private HouseController houseController;
	
	private MockMvc mockMvc;
	
	private String BASE_URI = "/api/v1";
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(houseController).build();
	}
	
	@Test
	void givenHouse_whenCreated_thenReturnHouse() throws JsonProcessingException, Exception {
		HouseResponseDTO responseDTO = HouseUtil.getHouseResponseDTO();
		HouseCreateRequestDTO requestDTO = HouseUtil.getHouseCreateRequestDTO();
		
		Mockito.when(houseService.createHouse(Mockito.any(HouseCreateRequestDTO.class))).thenReturn(responseDTO);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post(BASE_URI + "/houses")
				.content(new ObjectMapper().writeValueAsString(requestDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
		
		Mockito.verify(houseService, Mockito.times(1)).createHouse(Mockito.any(HouseCreateRequestDTO.class));
	}
	
//	@Test
//	void givenNonExistingHouse_whenDeleted_thenThrowException() throws Exception {
//		
//		Mockito.doThrow(CustomEntityNotFoundException.class).when(houseService).deleteHouse(Mockito.anyLong());
//		
//		mockMvc.perform(MockMvcRequestBuilders
//				.delete(BASE_URI + "/houses/" + 1))
//				.andExpect(MockMvcResultMatchers.status().isInternalServerError())
//				.andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomEntityNotFoundException));
//		
//		Mockito.verify(houseService, Mockito.times(1)).deleteHouse(Mockito.anyLong());
//	}
	
	@Test
	void givenExistingHouse_whenDeleted_thenOk() throws Exception {
		
		Mockito.doNothing().when(houseService).deleteHouse(Mockito.anyLong());
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete(BASE_URI + "/houses/" + 1))
				.andExpect(MockMvcResultMatchers.status().isNoContent()).andReturn();
		
		Mockito.verify(houseService, Mockito.times(1)).deleteHouse(Mockito.anyLong());
	}
	
	@Test
	void given_whenRetrieved_thenReturn() throws JsonProcessingException, Exception {
		List<HouseResponseDTO> responseDTOs = HouseUtil.getHouseResponseDTOs(3);
		
		Mockito.when(houseService.getAllHouses()).thenReturn(responseDTOs);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get(BASE_URI + "/houses")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		Mockito.verify(houseService, Mockito.times(1)).getAllHouses();
	}
	
	@Test
	void givenExistingHouse_whenRetrieved_thenReturn() throws JsonProcessingException, Exception {
		HouseResponseDTO responseDTO = HouseUtil.getHouseResponseDTO();
		
		Mockito.when(houseService.getHouseById(Mockito.anyLong())).thenReturn(responseDTO);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get(BASE_URI + "/houses/" + 1)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		Mockito.verify(houseService, Mockito.times(1)).getHouseById(Mockito.anyLong());
	}
	
	@Test
	void givenCity_whenRetrieved_thenReturn() throws JsonProcessingException, Exception {
		List<HouseResponseDTO> responseDTOs = HouseUtil.getHouseResponseDTOs(3);
		
		Mockito.when(houseService.getAllHousesByCity(Mockito.anyString())).thenReturn(responseDTOs);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get(BASE_URI + "/houses-by-city")
				.param("city", "test")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		Mockito.verify(houseService, Mockito.times(1)).getAllHousesByCity(Mockito.anyString());
	}
	
	@Test
	void givenExistingHouse_whenUpdated_thenReturn() throws JsonProcessingException, Exception {
		HouseResponseDTO responseDTO = HouseUtil.getHouseResponseDTO();
		HouseUpdateRequestDTO requestDTO = HouseUtil.getHouseUpdateRequestDTO();
		
		Mockito.when(houseService.updateHouse(Mockito.anyLong(), Mockito.any(HouseUpdateRequestDTO.class))).thenReturn(responseDTO);
		
		mockMvc.perform(MockMvcRequestBuilders
				.put(BASE_URI + "/houses/" + 1)
				.content(new ObjectMapper().writeValueAsString(requestDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		Mockito.verify(houseService, Mockito.times(1)).updateHouse(Mockito.anyLong(), Mockito.any(HouseUpdateRequestDTO.class));
	}
	
	@Test
	void givenCountry_whenRetrieved_thenReturn() throws JsonProcessingException, Exception {
		List<HouseResponseDTO> responseDTOs = HouseUtil.getHouseResponseDTOs(3);
		
		Mockito.when(houseService.getAllHousesContainingCountry(Mockito.anyString())).thenReturn(responseDTOs);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get(BASE_URI + "/houses-by-country")
				.param("country", "test")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		Mockito.verify(houseService, Mockito.times(1)).getAllHousesContainingCountry(Mockito.anyString());
	}

}