package isa.avioCompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.avioCompany.model.Class;

public interface ClassRepository extends JpaRepository<Class,Long>{

	List<Class> findByFlightId(Long id);
}
