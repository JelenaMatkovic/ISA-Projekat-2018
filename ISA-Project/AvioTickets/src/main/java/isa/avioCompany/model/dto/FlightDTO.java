package isa.avioCompany.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class FlightDTO {

	private Long id;

	private Date dateAndTimeStart;

	private Date dateAndTimeEnd;

	private Date timeOfTravel;
	
	private Double lengthOfTravel;

	private String typeOfTravel;

	private String pathCode;

	private Double rating;
	
	private Long air_plane_id;
	
	private Long avio_company_id;
	
	private Long path_id;

}
