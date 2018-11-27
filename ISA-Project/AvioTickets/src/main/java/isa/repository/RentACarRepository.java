package isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.model.RentACar;

@Repository
public interface RentACarRepository extends JpaRepository<RentACar, Long>{
	
	boolean existsByAddress(String address);
}
