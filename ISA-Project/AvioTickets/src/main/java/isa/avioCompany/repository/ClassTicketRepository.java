package isa.avioCompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.avioCompany.model.ClassTicket;
import isa.avioCompany.model.ClassTicketPK;

public interface ClassTicketRepository extends JpaRepository<ClassTicket,ClassTicketPK> {
	List<ClassTicket> findByTicketId(Long id);
}
