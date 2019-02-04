package isa.avioCompany.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FlightDTO {

	private Long id;
	
	private String pathCode;

	private Long starting_point_id;

	private Long destination_id;
	
	private Date dateAndTimeStart;

	private Date dateAndTimeEnd;
	
	private Double lengthOfTravel;
	
	private Date timeOfTravel;
	
	private Integer numberOfTransfer;

	private String destinationOfTransfer;

	private String typeOfPath;
	
	private String additionalServices;
	
	private Date dateAndTimeStartReturn;

	private Date dateAndTimeEndReturn;

	private Double rating;
	
	private Boolean deleted;
	
	private Long avio_company_id;
	
	
	
	
	
	


}
