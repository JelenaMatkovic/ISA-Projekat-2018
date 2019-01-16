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
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTake;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateReturn;
	
	private String placeTake;
	
	private String placeReturn;
}
