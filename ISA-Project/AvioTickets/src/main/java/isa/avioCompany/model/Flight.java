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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String pathCode;
	
	@Column(nullable=false)
	private Integer numberOfSegments;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "starting_point_id")
	private Destination startingPoint;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
	private Destination destination;

	@Column(nullable=false)
	private Date dateAndTimeStart;
	
	@Column(nullable=false)
	private Date dateAndTimeEnd;
	
	@Column(nullable=false)
	private Double lengthOfTravel;
	
	@Column
	private Date timeOfTravel;
	
	@Column(nullable=false)
	private Integer numberOfTransfer;

	@Column(nullable=false)
	private String destinationOfTransfer;
	
	@Column(nullable=false)
	private String typeOfPath;
	
	@Column
	private String additionalServices;
	
	@Column
	private Date dateAndTimeStartReturn;
	
	@Column
	private Date dateAndTimeEndReturn;

	@Column
	private Double rating;
	
	@Column(nullable=false)
	private Boolean deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avio_company_id")
	private AvioCompany avioCompany;
	
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private Set<Class> clas;
	
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
	private Set<Luggage> luggage;

}
