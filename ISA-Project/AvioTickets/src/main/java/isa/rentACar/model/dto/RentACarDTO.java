package isa.rentACar.model.dto;

import lombok.Data;

@Data
public class RentACarDTO {
	
	private Long id;
	
	private String name;
	
	private String address;
	
	private String description;
	
	private Double averageRating;
	
}
