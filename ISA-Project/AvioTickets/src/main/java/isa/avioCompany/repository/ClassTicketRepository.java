package isa.avioCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.avioCompany.model.ClassTicket;
import isa.avioCompany.model.ClassTicketPK;

public interface ClassTicketRepository extends JpaRepository<ClassTicket,ClassTicketPK> {

}
