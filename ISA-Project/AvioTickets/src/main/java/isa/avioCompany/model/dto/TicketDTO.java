package isa.avioCompany.model.dto;

import lombok.Data;

@Data
public class TicketDTO {
	
	private Long id;

	private Double price;

	private Double discount;
	
	private Long spot_id;
	
	private Long user_id;
	
	private Long flight_id;

	private Long avio_company_id;
	
}
