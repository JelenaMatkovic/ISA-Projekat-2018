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

import isa.rentACar.model.RentACar;
import lombok.Data;

@Data
@Entity
@Table(name="hotels")

public class Hotel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name",nullable=false,length=50)
	private String name;
	
	@Column(name="location",nullable=false)
	private String location;
	
	@Column(name="address",nullable=false,length=100)
	private String address;
	
	@Column(name="description",nullable=true)
	private String description;
	
	@Column(name="rating",nullable=true)
	private double rating;
	
	@OneToMany(mappedBy="hotel")
	private Set<Room> rooms;
	
	@OneToMany(mappedBy="hotel")
	private Set<Extras> extras;
	
	@OneToMany(mappedBy="hotel")
	private Set<Discount> discounts;
	
}
