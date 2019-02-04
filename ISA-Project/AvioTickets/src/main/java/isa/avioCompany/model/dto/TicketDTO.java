package isa.avioCompany.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TicketDTO {
	
	private Long id;
	
	private Long user_id;
	
	private Integer numberOfSeats;
	
	private Date dateAndTimeTicket;
	
	private Double discount;
	
	private Boolean fastReservation;
	
	private Long flight_id;
	
	private Boolean deleted;
	
}
