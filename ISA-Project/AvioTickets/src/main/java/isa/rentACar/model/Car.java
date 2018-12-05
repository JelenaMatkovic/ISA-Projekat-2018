package isa.rentACar.model;

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
@Table(name="car")
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=100)
	private String name;
	
	@Column(nullable=false,length=100)
	private String model;
	
	@Column(nullable=false,length=100)
	private String brand;
	
	@Column(name="year",nullable=false)
	private int year;
	
	@Column(name="price",nullable=false)
	private double price;
	
	@Column(name="seats",nullable=false)
	private int seats;
	
	@ManyToOne
	@JoinColumn(name = "rent_a_car_id", nullable = false)
	private RentACar rentACar;
}
