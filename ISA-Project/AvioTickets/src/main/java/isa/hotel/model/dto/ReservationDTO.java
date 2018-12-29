package isa.hotel.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReservationDTO {

	private Long id;
	
	private Date dateOfArrival;
	
	private Date dateOfDeparture;
	
	private double price;
	
	private Long reservatedRoomID;
}
