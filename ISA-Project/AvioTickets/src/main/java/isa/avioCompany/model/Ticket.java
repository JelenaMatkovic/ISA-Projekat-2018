package isa.avioCompany.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import isa.user.model.User;
import lombok.Data;

@Data
@Entity 
@Inheritance(strategy = InheritanceType.JOINED)
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User user;
	
	@Column(nullable=false)
	private Integer numberOfSeats;
	
	@Column(nullable=false)
	private Date dateAndTimeTicket;
	
	@Column(nullable=false)
	private Double discount;
	
	@Column(nullable=false)
	private Boolean fastReservation;
	
	@Column(nullable=false)
	private Boolean deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id")
	private Class clas;
	
}
