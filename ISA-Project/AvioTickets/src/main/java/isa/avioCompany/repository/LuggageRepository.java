package isa.avioCompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.avioCompany.model.Luggage;

public interface LuggageRepository extends JpaRepository<Luggage,Long>{

	List<Luggage> findByFlightId(Long id);
}
