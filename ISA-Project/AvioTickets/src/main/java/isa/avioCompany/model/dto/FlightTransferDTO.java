package isa.avioCompany.model.dto;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FlightTransferDTO {
	
	private Long id;
	
	private String nameOfAvioCompany;

	private String pathCode;
	
	private Boolean done;
	
	private Boolean deleted;
	
	private Integer numberOfSegments;
	
	private DestinationDTO starting_point_id;
	
	private DestinationDTO destination_id;
	
	private Date dateAndTimeStart;
	
	private Date dateAndTimeEnd;
	
	private Double lengthOfTravel;
	
	private String timeOfTravel;
	
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
