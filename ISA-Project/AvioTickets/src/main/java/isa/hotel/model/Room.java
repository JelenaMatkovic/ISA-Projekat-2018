package isa.hotel.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="rooms")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 10)
	private String name;
	
	@Column(name = "floor", nullable = false, length = 50)
	private int floor;
	
	@Column(name = "beds", nullable = false)
	private int beds;
	
	@Column(name = "rating", nullable = false)
	private double rating;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;
	
	@OneToMany(mappedBy = "room")
	private Set<DefinedRoomPrice> definedRoomPrices;
	
	@OneToMany(mappedBy = "reservatedRoom")
	private Set<Reservation> reservations;
	
	@ManyToOne
	@JoinColumn(name = "room_type_id", nullable = false)
	private RoomType roomType;
}
