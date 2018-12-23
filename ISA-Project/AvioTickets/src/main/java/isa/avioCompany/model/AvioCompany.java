package isa.avioCompany.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class AvioCompany {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=100)
	private String name;
	
	@Column(nullable=false,length=100)
	private String address;

	@Column(nullable=false,length=100)
	private String description;
	
	@OneToMany(mappedBy = "avioCompany", cascade = CascadeType.ALL)
	private Set<Path> destination;
	
	@OneToMany(mappedBy = "avioCompany", cascade = CascadeType.ALL)
	private Set<Flight> flights;
	
	@OneToMany(mappedBy = "avioCompany", cascade = CascadeType.ALL)
	private Set<AirPlane> airplanes;
	
	@OneToMany(mappedBy = "avioCompany", cascade = CascadeType.ALL)
	private Set<DiscountTickets> discountTickets;
	
	@OneToMany(mappedBy = "avioCompany", cascade = CascadeType.ALL)
	private Set<Luggage> luggage;
	
	@Column(nullable=false)
	private Double rating;
}
