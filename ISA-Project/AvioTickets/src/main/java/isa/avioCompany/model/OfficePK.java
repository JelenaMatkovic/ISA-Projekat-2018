package isa.avioCompany.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class OfficePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long avioCompany;
	
	private Long destination;
	
}
