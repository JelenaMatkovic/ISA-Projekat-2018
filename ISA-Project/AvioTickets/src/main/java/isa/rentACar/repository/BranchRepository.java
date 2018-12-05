package isa.rentACar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.rentACar.model.Branch;


public interface BranchRepository extends JpaRepository<Branch, Long> {

	public Optional<Branch> findByRentACarIdAndId(Long rentACarId, Long id);
	
	public boolean existsByRentACarIdAndId(Long rentACarId, Long id);
	
	public List<Branch> findByRentACarId(Long rentACarId);
}
