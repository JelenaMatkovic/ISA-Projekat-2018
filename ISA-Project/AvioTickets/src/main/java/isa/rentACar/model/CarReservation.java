package isa.rentACar.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import isa.user.model.User;
import lombok.Data;

@Data
@Entity
@Table(name="car_reservation")
public class CarReservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name = "is_quick_reservation", nullable = false)
	private Boolean isQuickReservation; 
	
	@Column(name="date_take", nullable=false)
	private LocalDateTime dateTake;
	
	@Column(name="date_return",  nullable=false)
	private LocalDateTime dateReturn;

	@ManyToOne
	@JoinColumn(name="place_take",nullable=false)
	private Branch placeTake;
	
	@ManyToOne
	@JoinColumn(name="place_return",nullable=false)
	private Branch placeReturn;
	
	
	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
}
