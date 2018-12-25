package isa.avioCompany.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class SpotInTheAirPlane {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private Integer numberOfSeats;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clas_id")
	private Class clas;
	
	@OneToOne(mappedBy = "spotInTheAirplane", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
	private Ticket ticket;
}
