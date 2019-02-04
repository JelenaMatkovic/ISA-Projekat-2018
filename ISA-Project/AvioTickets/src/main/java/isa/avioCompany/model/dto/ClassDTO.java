package isa.avioCompany.model.dto;

import lombok.Data;

@Data
public class ClassDTO {
	
	private Long id;
	
	private String type;
	
	private Integer numberOfSeats;
	
	private Double priceForAdults;
	
	private Double priceForKids;
	
	private Boolean deleted;
	
	private Long flight_id;
	
}
