package isa.avioCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.avioCompany.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination,Long> {
	boolean existsByNameOfAirPort(String nameOfTown);
	Destination findByNameOfAirPort(String nameofTown);
}
