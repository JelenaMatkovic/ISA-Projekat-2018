package isa.avioCompany.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "path_id")
	private Path path;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAndTimeStart;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAndTimeEnd;
	
	@Temporal(TemporalType.TIME)
	private Date timeOfTravel;
	
	@Column(nullable=false)
	private Double lengthOfTravel;
	
	@Column(nullable=false,length=100)
	private String typeOfTravel;
	
	@ElementCollection(targetClass=String.class)
	private Set<String> additionalServices;
	
	@OneToMany(mappedBy = "flight" , cascade = CascadeType.ALL)
	private Set<Ticket> ticket;
	
	@Column(nullable=false,length=100)
	private String pathCode;
	
	@Column(nullable=false)
	private Double rating;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avio_company_id")
	private AvioCompany avioCompany;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "air_plane_id")
	private AvioCompany airPlane;
}
