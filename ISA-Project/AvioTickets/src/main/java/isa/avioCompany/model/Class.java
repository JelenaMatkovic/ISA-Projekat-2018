package isa.avioCompany.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Class {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=100)
	private String type;
	
	@Column(nullable=false)
	private Integer numberOfSeats;
	
	@Column(nullable=false)
	private Double price;
	
	@Column(nullable=false)
	private Boolean deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	@OneToMany(mappedBy = "clas", cascade = CascadeType.ALL)
	private Set<Ticket> ticket;
	
}
