package com.mingdos.ticketmaster.repository;

import com.mingdos.ticketmaster.entity.DiagnosticLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosticLogRepository extends JpaRepository<DiagnosticLog, Long> {
    List<DiagnosticLog> findByTicketId(Long ticketId);
}
