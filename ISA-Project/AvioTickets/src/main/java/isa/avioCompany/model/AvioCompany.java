package isa.avioCompany.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class AvioCompany {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="avio_company_id")
	private Long id;
	
	@Column(nullable=false,length=100)
	private String name;
	
	@Column(nullable=false,length=100)
	private String address;

	@Column(nullable=false,length=100)
	private String description;
	
	@Column(nullable=false)
	private Double rating;
	
	@Column(nullable=false)
	private Boolean deleted;
	
	@OneToMany(mappedBy = "avioCompany", cascade = CascadeType.ALL)
	private Set<Flight> flights;
	
	@OneToMany(mappedBy = "avioCompany", cascade = CascadeType.ALL)
	private Set<Office> office;
	
}
