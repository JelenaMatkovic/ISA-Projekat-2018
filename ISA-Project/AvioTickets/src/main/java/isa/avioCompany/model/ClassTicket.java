package isa.avioCompany.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
@IdClass(ClassTicketPK.class)
public class ClassTicket {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Id
	@JoinColumn(name = "clas_id" , referencedColumnName="clas_id")
	private Class clas;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Id
	@JoinColumn(name = "ticket_id" , referencedColumnName="ticket_id")
	private Ticket ticket;
	
	@Column(nullable=false)
	private Boolean deleted;

}
