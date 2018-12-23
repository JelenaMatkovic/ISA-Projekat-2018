package isa.avioCompany.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class AirPlane {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=100)
	private String name;
	
	@OneToMany(mappedBy = "airPlane", cascade = CascadeType.ALL)
	private Set<Class> clas;
	
	@OneToMany(mappedBy = "airPlane", cascade = CascadeType.ALL)
	private Set<Flight> flights;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avio_company_id")
	private AvioCompany avioCompany;
}
