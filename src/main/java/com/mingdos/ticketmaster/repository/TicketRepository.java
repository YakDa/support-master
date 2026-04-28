package com.mingdos.ticketmaster.repository;

import com.mingdos.ticketmaster.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
