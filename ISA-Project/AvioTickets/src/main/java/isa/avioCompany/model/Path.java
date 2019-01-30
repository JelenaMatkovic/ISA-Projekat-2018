package isa.avioCompany.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
public class Path {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destination_start")
	private Destination destinationStart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destination_end")
	private Destination destinationEnd;
	
	@Column(nullable=false)
	private Integer numberOfTransfer;

	@ElementCollection(targetClass=String.class)
	private Set<String> destinationOfTransfer;
	
	@OneToMany(mappedBy = "path", cascade = CascadeType.ALL)
	private Set<Flight> flights;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avio_company_id")
	private AvioCompany avioCompany;
	
	

}
