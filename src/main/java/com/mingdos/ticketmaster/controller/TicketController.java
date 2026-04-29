package com.mingdos.ticketmaster.controller;

import com.mingdos.ticketmaster.dto.TicketRequest;
import com.mingdos.ticketmaster.entity.Ticket;
import com.mingdos.ticketmaster.entity.TicketStatus;
import com.mingdos.ticketmaster.service.TicketService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody TicketRequest request) {
        Ticket ticket = ticketService.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicket(id);
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Ticket> updateStatus(@PathVariable Long id, @RequestBody TicketStatus newStatus) {
        Ticket ticket = ticketService.updateStatus(id, newStatus);
        return ResponseEntity.ok(ticket);
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<Ticket> assignAgent(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String agentId = body.get("agentId");
        Ticket ticket = ticketService.assignAgent(id, agentId);
        return ResponseEntity.ok(ticket);
    }
}
