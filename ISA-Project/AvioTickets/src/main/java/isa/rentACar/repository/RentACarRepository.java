package isa.rentACar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.rentACar.model.RentACar;

@Repository
public interface RentACarRepository extends JpaRepository<RentACar, Long>{
	
	boolean existsByAddress(String address);
}
