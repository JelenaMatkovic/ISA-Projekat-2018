package isa.avioCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.avioCompany.model.Flight;

public interface FlightRepository extends JpaRepository<Flight,Long>{

}
