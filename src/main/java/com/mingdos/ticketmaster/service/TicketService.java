package com.mingdos.ticketmaster.service;

import com.mingdos.ticketmaster.dto.DiagnosticLogRequest;
import com.mingdos.ticketmaster.dto.TicketRequest;
import com.mingdos.ticketmaster.entity.DiagnosticLog;
import com.mingdos.ticketmaster.entity.Ticket;
import com.mingdos.ticketmaster.entity.TicketStatus;
import com.mingdos.ticketmaster.exception.NotFoundException;
import com.mingdos.ticketmaster.repository.DiagnosticLogRepository;
import com.mingdos.ticketmaster.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private DiagnosticLogRepository diagnosticLogRepository;

    public Ticket createTicket(TicketRequest request) {
        Ticket ticket = new Ticket();
        ticket.setDeviceId(request.getDeviceId());
        ticket.setCustomerId(request.getCustomerId());
        ticket.setIssue(request.getIssue());
        ticket.setPriority(request.getPriority());
        ticket.setTicketStatus(TicketStatus.OPEN);
        return ticketRepository.save(ticket);
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Ticket not found: " + id));
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateStatus(Long id, TicketStatus newStatus) {
        Ticket ticket = getTicket(id);
        validateStatusTransition(ticket.getTicketStatus(), newStatus);

        if(newStatus == TicketStatus.RESOLVED) {
            long diagnosticCount = diagnosticLogRepository.findByTicketId(id).size();
            if(diagnosticCount==0) {
                throw new IllegalStateException("Cannot resolve ticket without diagnostics");
            }

            ticket.setResolvedAt(LocalDateTime.now());

        }
        ticket.setTicketStatus(newStatus);
        return ticketRepository.save(ticket);
    }

    public Ticket assignAgent(Long id, String agentId) {
        Ticket ticket = getTicket(id);
        ticket.setAssignedAgentId(agentId);
        return ticketRepository.save(ticket);
    }

    public DiagnosticLog addDiagnostic(Long ticketId, DiagnosticLogRequest request) {
        DiagnosticLog log = new DiagnosticLog();
        log.setTicketId(ticketId);
        log.setStep(request.getStep());
        log.setResult(request.getResult());
        log.setPerformedBy(request.getPerformedBy());
        log.setTimestamp(LocalDateTime.now());

        return diagnosticLogRepository.save(log);

    }

    public List<DiagnosticLog> getDiagnostics(Long ticketId) {
        return diagnosticLogRepository.findByTicketId(ticketId);
    }

    private void validateStatusTransition(TicketStatus current, TicketStatus next) {
        // State machine rules from spec
        if(current==TicketStatus.OPEN && !(next==TicketStatus.IN_PROGRESS || next == TicketStatus.CLOSED)) {
            throw new IllegalArgumentException("Cannot transition from OPEN to " + next);
        }
        if (current == TicketStatus.IN_PROGRESS && !(next == TicketStatus.WAITING_PARTS || next
                == TicketStatus.RESOLVED)) {
            throw new IllegalArgumentException("Cannot transition from IN_PROGRESS to " + next);
        }
        if (current == TicketStatus.WAITING_PARTS && next != TicketStatus.IN_PROGRESS) {
            throw new IllegalArgumentException("Cannot transition from WAITING_PARTS to " +
                    next);
        }
        if (current == TicketStatus.RESOLVED && !(next == TicketStatus.CLOSED || next ==
                TicketStatus.IN_PROGRESS)) {
            throw new IllegalArgumentException("Cannot transition from RESOLVED to " + next);
        }

    }
}
