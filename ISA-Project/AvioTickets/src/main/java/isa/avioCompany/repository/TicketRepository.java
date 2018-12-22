package isa.avioCompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.avioCompany.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long>{

}
