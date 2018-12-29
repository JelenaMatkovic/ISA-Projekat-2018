package isa.hotel.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DiscountDTO {

	private Long id;
	
	private String discountCombination;
	
	private double discount;
	
	private Date from;
	
	private Date to;
	
	private String discountType;
	
	private Long hotelID;
}
