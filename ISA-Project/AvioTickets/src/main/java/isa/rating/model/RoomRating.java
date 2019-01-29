package isa.rating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import isa.hotel.model.Reservation;
import isa.hotel.model.Room;
import isa.user.model.User;
import lombok.Data;

@Data
@Entity
@Table(name="room_rating")
public class RoomRating {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="room_id",nullable=false)
	private Room room;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="room_reservation_id",nullable=false)
	private Reservation roomReservation;
	
	@Column(name="rating",nullable=false)
	private int rating;
	
}
