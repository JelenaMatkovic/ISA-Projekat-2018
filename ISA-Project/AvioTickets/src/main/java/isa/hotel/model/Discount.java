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
@Table(name="discounts")
public class Discount {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="discount_combination",nullable=false)
	private String discountCombination;
	
	@Column(name="discount",nullable=false)
	private double discount;
	
	@Column(name="startDate",nullable=false)
	private Date from;
	
	@Column(name="endDate",nullable=false)
	private Date to;
	
	@Column(name = "discount_type", nullable = false)
	private String discountType;
	
	@ManyToOne
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;
	
	@OneToMany(mappedBy = "discount")
	private Set<RoomPrice> roomPrices;
	
}