package isa.avioCompany.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Luggage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private Double quantityOf;
	
	@Column(nullable=false)
	private Double quantityTo;
	
	@Column(nullable=false)
	private Double weightOf;
	
	@Column(nullable=false)
	private Double weightTo;
	
	@Column(nullable=false)
	private Double dimensionsOf;
	
	@Column(nullable=false)
	private Double dimensionsTo;

	@Column(nullable=false)
	private Double price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avio_company_id")
	private AvioCompany avioCompany;
}
