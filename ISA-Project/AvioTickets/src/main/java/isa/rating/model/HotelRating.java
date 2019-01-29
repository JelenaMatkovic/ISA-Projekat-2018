package isa.rating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import isa.hotel.model.Hotel;
import isa.hotel.model.Reservation;
import isa.user.model.User;
import lombok.Data;

@Data
@Entity
@Table(name="hotel_rating")
public class HotelRating {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="hotel_id",nullable=false)
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="room_reservation_id",nullable=false)
	private Reservation roomReservation;
	
	@Column(name="rating",nullable=false)
	private int rating;
	
}
