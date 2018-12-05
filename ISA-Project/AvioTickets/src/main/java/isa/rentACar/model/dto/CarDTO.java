package isa.rentACar.model.dto;

import lombok.Data;

@Data
public class CarDTO {
	
	private Long id;
	
	private String name;
	
	private String model;
	
	private String brand;
	
	private int year;
	
	private double price;
	
	private int seats;
	
	private Long rentACarId;
}
