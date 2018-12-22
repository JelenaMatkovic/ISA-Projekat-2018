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
	private String tip;
	
	@Column(nullable=false)
	private Integer numberOfSeats;
	
	@OneToMany(mappedBy = "clas", cascade = CascadeType.ALL)
	private Set<SpotInTheAirPlane> spotInTheAirPlane;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "air_plane_id")
	private AvioCompany airPlane;
	
}
