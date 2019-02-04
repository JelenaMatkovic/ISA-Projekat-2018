package isa.avioCompany.model.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FlightTransferDTO {
	
	private Long id;

	private String pathCode;
	
	private DestinationDTO starting_point_id;
	
	private DestinationDTO destination_id;
	
	private Date dateAndTimeStart;
	
	private Date dateAndTimeEnd;
	
	private Double lengthOfTravel;
	
	private List<DestinationDTO> destinationOfTransfer;
	
	private String typeOfPath;
	
	private String additionalServices;
	
	private Date dateAndTimeStartReturn;
	
	private Date dateAndTimeEndReturn;
	
	private ClassTransferDTO ecconomic;
	
	private ClassTransferDTO business;
	
	private ClassTransferDTO first;
	
	private LuggageTransferDTO luggage;
	
	
}
