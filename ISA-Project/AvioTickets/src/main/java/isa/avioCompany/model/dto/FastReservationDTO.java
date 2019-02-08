package isa.avioCompany.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FastReservationDTO {
	
	private Long id;
	
	private String nameOfAvioCompany;

	private DestinationDTO starting_point_id;
	
	private DestinationDTO destination_id;
	
	private Date dateAndTimeStart;
	
	private Date dateAndTimeEnd;
	
	private Integer numberOfSeat;
	
	private Double price;
	
	private Double discount;
	
}
