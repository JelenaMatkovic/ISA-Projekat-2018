package isa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.model.Car;

public interface CarRepository extends JpaRepository<Car,Long>{
	
	public Optional<Car> findByRentACarIdAndId(Long rentACarId, Long id);
	
	public boolean existsByRentACarIdAndId(Long rentACarId, Long id);
	
	public List<Car> findByRentACarId(Long rentACarId);
}
