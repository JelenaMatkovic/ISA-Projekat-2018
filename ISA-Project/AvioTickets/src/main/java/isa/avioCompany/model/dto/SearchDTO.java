package isa.avioCompany.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SearchDTO {

	private String typeOfPath;
	
	private String numberOfAdults;
	
	private String numberOfChilds;
	
	private String clas;

	private String starting_pointTown;
	
	private String starting_pointCountry;
	
	private String destinationTown;
	
	private String destinationCountry;
	
	private Date dateAndTimeStart;
	
	private Date dateAndTimeEnd;
}
