package isa.rentACar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.rentACar.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>{
	
	public Optional<Car> findByRentACarIdAndId(Long rentACarId, Long id);
	
	public boolean existsByRentACarIdAndId(Long rentACarId, Long id);
	
	public List<Car> findByRentACarId(Long rentACarId);
}
