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
public class Destination {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false,length=100)
	private String name;
	
	@Column(nullable=false,length=100)
	private String description;
	
	@OneToMany(mappedBy = "destinationStart", cascade = CascadeType.ALL)
	private Set<Path> pathStart;
	
	@OneToMany(mappedBy = "destinationEnd", cascade = CascadeType.ALL)
	private Set<Path> pathEnd;

}
