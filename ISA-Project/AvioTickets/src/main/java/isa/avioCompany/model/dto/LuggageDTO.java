package isa.avioCompany.model.dto;

import lombok.Data;

@Data
public class LuggageDTO {
	
	private Long id;
	
	private Double maxQuantity;
	
	private Double maxWeight;
	
	private Double maxDimensions;
	
	private Boolean deleted;
	
	private Long flight_id;
}
