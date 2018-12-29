package isa.hotel.model.dto;

import lombok.Data;

@Data
public class HotelDTO {
	
	private Long id;
	
	private String name;
	
	private String location;
	
	private String address;
	
	private String description;
	
	private double rating;
}
