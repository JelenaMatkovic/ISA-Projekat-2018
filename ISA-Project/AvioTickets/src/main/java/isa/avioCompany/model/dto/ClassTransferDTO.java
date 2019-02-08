package isa.avioCompany.model.dto;

import lombok.Data;

@Data
public class ClassTransferDTO {
	
	private Integer numberOfSeats;
	
	private Double price;
	
	private String deletedSeats;
	
	private String occupiedSeats;
}
