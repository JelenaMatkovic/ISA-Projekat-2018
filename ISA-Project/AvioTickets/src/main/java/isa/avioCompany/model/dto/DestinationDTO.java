package isa.avioCompany.model.dto;

import lombok.Data;

@Data
public class DestinationDTO {

	private Long id;
	
	private String nameOfTown;
	
	private String nameOfCountry;
	
	private String nameOfAirPort;
	
	private String description;
	
	private Boolean deleted;
	
}
