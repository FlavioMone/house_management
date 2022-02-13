package com.example.house_management.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "houses")
@EntityListeners(AuditingEntityListener.class)
public class House {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "'address' field of House cannot be null")
	@Column(name = "address", nullable = false)
	private String address;
	
	@NotNull(message = "'zip_code' field of House cannot be null")
	@Column(name = "zip_code", nullable = false)
	private Integer zipCode;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;
	
	@Min(value = 0, message = "Number of balconies cannot be negative")
	@Column(name = "number_of_balconies")
	private Integer numberOfBalconies;

	@Min(value = 1, message = "Number of bedrooms should not be less than 1")
	@Column(name = "number_of_bedrooms")
	private Integer numberOfBedrooms;
	
	@Min(value = 1, message = "Number of bathrooms should not be less than 1")
	@Column(name = "number_of_bathrooms")
	private Integer numberOfBathrooms;
	
	@CreatedDate
	@Column(name = "created_on", nullable = false, updatable = false)
	private LocalDateTime createdOn;
	
	@LastModifiedDate
	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

}
