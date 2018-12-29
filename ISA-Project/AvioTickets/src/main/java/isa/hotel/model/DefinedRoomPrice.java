package isa.hotel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class DefinedRoomPrice {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "room_price_id", nullable = false)
	private RoomPrice roomPrice;

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
}
