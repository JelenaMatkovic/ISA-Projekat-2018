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
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="room_prices")

public class RoomPrice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="startDate",nullable=false)
	private Date from;
	
	@Column(name="endDate",nullable=false)
	private Date to;
	
	@Column(name="room_price",nullable=false)
	private double roomPrice;
	
	@ManyToOne
	@JoinColumn(name = "type_id", nullable = false)
	private ReservationType reservationType;
	
	@ManyToOne
	@JoinColumn(name = "discount_id")
	private Discount discount;
	
	@OneToMany(mappedBy = "roomPrice")
	private Set<DefinedRoomPrice> definedRoomPrices;
	
	
}
