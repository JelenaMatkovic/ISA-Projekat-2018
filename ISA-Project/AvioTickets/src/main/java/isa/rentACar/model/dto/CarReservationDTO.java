package isa.rentACar.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.Data;

@Data
public class CarReservationDTO {
	
	private Long id;
	
	private Long carId;
		
	private String carName;
	
	private Double price;

	private Double totalPrice;

	private Boolean isQuickReservation;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTake;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateReturn;
	
	private Long placeTake;
	
	private Long placeReturn;
	
	private String placeTakeAddress;
	
	private String placeReturnAddress;
	
	private String rentACarName;
	
	private Long rentACarId;
	
	private boolean canRateCar;
	
	private boolean canRateRentACar;
}
