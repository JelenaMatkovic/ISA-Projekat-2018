package isa.hotel.model.dto;

import lombok.Data;

@Data
public class RoomDTO {
	
	private Long id;
	
	private String name;
	
	private int floor;
	
	private int beds;
	
	private double rating;
	
	private Long hotelID;
	
	private Long roomTypeID;
}
