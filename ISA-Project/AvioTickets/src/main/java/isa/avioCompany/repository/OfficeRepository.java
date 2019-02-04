package isa.avioCompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.avioCompany.model.Office;
import isa.avioCompany.model.OfficePK;

public interface OfficeRepository extends JpaRepository<Office,OfficePK> {
	List<Office> findByAvioCompanyId(Long id);
	boolean existsByAvioCompanyIdAndDestinationId(Long avioId,Long destId);
	void deleteByAvioCompanyIdAndDestinationId(Long avioId,Long destId);
	List<Office> findByDestinationId(Long id);
}
