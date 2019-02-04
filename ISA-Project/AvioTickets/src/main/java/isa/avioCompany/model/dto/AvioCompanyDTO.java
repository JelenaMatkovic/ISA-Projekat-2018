package isa.avioCompany.model.dto;

import lombok.Data;

@Data
public class AvioCompanyDTO {

	private Long id;

	private String name;

	private String address;

	private String description;

	private Double rating;
	
	private Boolean deleted;
}
