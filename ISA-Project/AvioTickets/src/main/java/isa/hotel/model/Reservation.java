package isa.hotel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="reservations")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="date_of_arrival",nullable=false)
	private Date dateOfArrival;
	
	@Column(name="date_of_departure",nullable=false)
	private Date dateOfDeparture;
	
	@Column(name="price",nullable=false)
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "reservated_room_id", nullable = false)
	private Room reservatedRoom;
}