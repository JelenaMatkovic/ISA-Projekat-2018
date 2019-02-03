package isa.rentACar.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RentACarIncomeDTO {

	private Long rentACarId;
	
	private Double income;
	
	private LocalDate dateFrom;
	
	private LocalDate dateTo;
	
}
