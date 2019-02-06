package isa.avioCompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.avioCompany.model.Flight;

public interface FlightRepository extends JpaRepository<Flight,Long>{
	
	Flight findByPathCode(String id);
	
	List<Flight> findByAvioCompanyId(Long idAvio);
	

}
