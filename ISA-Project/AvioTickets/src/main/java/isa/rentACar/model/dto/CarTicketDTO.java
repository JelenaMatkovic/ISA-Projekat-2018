package isa.rentACar.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.Data;

@Data
public class CarTicketDTO {

	private Long id;
	
	private Long carId;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTake;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateReturn;
	
	private Long placeTake;
	
	private Long placeReturn;
	
	private Long rentACarId;
	
	private String carName;
	
	private String placeTakeAddress;
	
	private String placeReturnAddress;
	
}
