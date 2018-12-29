package isa.hotel.model.dto;

import java.util.Date;

import lombok.Data;


@Data
public class RoomPriceDTO {
	
	private Long id;
	
	private Date from;
	
	private Date to;
	
	private double roomPrice;
	
	private Long reservationTypeID;
	
	private Long discountID;
}
