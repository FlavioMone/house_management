package com.example.house_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.house_management.model.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>, JpaSpecificationExecutor<House> {

	@Query("SELECT h FROM House h WHERE h.city = :city")
	public List<House> getAllHousesByCity(@Param("city") String city);
	
	@Query("SELECT h FROM House h WHERE LOWER(h.country) LIKE %:country%")
	public List<House> getAllHousesContainingCountry(@Param("country") String country);
	
	//Same as:
	//public List<House> getByAddressContainingIgnoreCase(String country);
	
}
