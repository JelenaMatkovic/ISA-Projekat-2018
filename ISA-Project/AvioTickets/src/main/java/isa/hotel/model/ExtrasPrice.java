package isa.hotel.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class ExtrasPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="price",nullable = false)
	private double price;
	
	@Column(name="startDate",nullable=false)
	private Date from;
	
	@Column(name="endDate",nullable=false)
	private Date to;
	
	@ManyToOne
	@JoinColumn(name = "extras_id", nullable = false)
	private Extras extras;
	
	@OneToMany(mappedBy = "extrasPrice")
	private Set<ReservationExtras> reservationExtras;
	
	
}
