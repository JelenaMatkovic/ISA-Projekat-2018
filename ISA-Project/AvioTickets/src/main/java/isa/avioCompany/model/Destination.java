package isa.avioCompany.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Destination {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="destination_id")
	private Long id;
	
	@Column(nullable=false,length=100)
	private String nameOfTown;
	
	@Column(nullable=false,length=100)
	private String nameOfCountry;
	
	@Column(nullable=false,length=100)
	private String nameOfAirPort;
	
	@Column(nullable=false,length=100)
	private String description;
	
	@Column(nullable=false)
	private Boolean deleted;
	
	@OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
	private Set<Office> office;
	
	@OneToOne(mappedBy = "startingPoint", cascade = CascadeType.ALL)
	private Flight flightStart;
	
	@OneToOne(mappedBy = "destination", cascade = CascadeType.ALL)
	private Flight flightEnd;

}
