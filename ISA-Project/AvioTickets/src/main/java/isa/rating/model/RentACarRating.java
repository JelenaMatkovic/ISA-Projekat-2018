package isa.rating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import isa.rentACar.model.CarReservation;
import isa.rentACar.model.RentACar;
import isa.user.model.User;
import lombok.Data;

@Data
@Entity
@Table(name="rent_a_car_rating")
public class RentACarRating {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="rent_a_car_id",nullable=false)
	private RentACar rentACar;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="car_reservation_id",nullable=false)
	private CarReservation carReservation;
	
	@Column(name="rating",nullable=false)
	private int rating;
	
}
