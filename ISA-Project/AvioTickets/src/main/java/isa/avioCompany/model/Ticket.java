package isa.avioCompany.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import isa.user.model.User;
import lombok.Data;

@Data
@Entity 
@Inheritance(strategy = InheritanceType.JOINED)
public class Ticket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ticket_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User user;
	
	@Column(nullable=false)
	private Integer numberOfSeats;
	
	@Column(nullable=false)
	private Long flightId;
	
	@Column(nullable=false)
	private Date dateAndTimeTicket;
	
	@Column
	private Double discount;
	
	@Column
	private Boolean fastReservation;
	
	@Column(nullable=false)
	private Boolean deleted;
	
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
	private Set<ClassTicket> classTicket;
	
	@OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
	private Set<NoUser> noUser;
	
	@Column
	private String passport;
	
	
}
