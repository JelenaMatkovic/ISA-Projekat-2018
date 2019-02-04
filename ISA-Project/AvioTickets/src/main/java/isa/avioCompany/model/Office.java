package isa.avioCompany.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
@IdClass(OfficePK.class)
public class Office {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Id
	@JoinColumn(name = "avio_company_id" , referencedColumnName="avio_company_id")
	private AvioCompany avioCompany;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Id
	@JoinColumn(name = "destination_id" , referencedColumnName="destination_id")
	private Destination destination;
	
	@Column(nullable=false)
	private Boolean deleted;

}
