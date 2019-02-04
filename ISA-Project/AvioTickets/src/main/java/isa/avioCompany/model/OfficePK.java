package isa.avioCompany.model;

import java.io.Serializable;

public class OfficePK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long avioCompany;
	
	private Long destination;
	
	public OfficePK() {
		
	}

	public Long getAvioCompany() {
		return avioCompany;
	}

	public void setAvioCompany(Long avioCompany) {
		this.avioCompany = avioCompany;
	}

	public Long getDestination() {
		return destination;
	}

	public void setDestination(Long destination) {
		this.destination = destination;
	}
	
}
