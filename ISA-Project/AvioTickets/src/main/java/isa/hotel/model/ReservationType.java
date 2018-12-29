package isa.hotel.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="reservation_types")
public class ReservationType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="type",nullable=false,length=50)
	private String type;
	
	@OneToMany(mappedBy = "reservationType")
	private Set<RoomPrice> priceList;
}
