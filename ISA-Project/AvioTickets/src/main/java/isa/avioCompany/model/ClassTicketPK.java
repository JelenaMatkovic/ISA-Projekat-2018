package isa.avioCompany.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClassTicketPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long clas;
	
	private Long ticket;
}
