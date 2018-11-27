package isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="rent_a_car")

public class RentACar {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name",nullable=false,length=50)
	private String name;
	
	@Column(name="address",nullable=false,length=100)
	private String address;
	
	@Column(name="description",nullable=true)
	private String description;
	
}
