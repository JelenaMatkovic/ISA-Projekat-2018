package isa.avioCompany.model.dto;

import lombok.Data;

@Data
public class ReservationDTO {
	
	private String firstName;
	
	private String lastName;
	
	private String passport;

	private Integer numberOfSeats;
	
	private String classType;

}
