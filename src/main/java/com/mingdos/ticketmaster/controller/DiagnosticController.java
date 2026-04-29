package com.mingdos.ticketmaster.controller;

import com.mingdos.ticketmaster.dto.DiagnosticLogRequest;
import com.mingdos.ticketmaster.entity.DiagnosticLog;
import com.mingdos.ticketmaster.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiagnosticController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/tickets/{id}/diagnostics")
    public ResponseEntity<DiagnosticLog> addDiagnostic(
            @PathVariable Long id,
            @Valid @RequestBody DiagnosticLogRequest request
            ) {
        DiagnosticLog log = ticketService.addDiagnostic(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(log);
    }

    @GetMapping("/tickets/{id}/diagnostics")
    public ResponseEntity<List<DiagnosticLog>> getDiagnostics(@PathVariable Long id) {
        List<DiagnosticLog> diagnosticLogs = ticketService.getDiagnostics(id);
        return ResponseEntity.ok(diagnosticLogs);
    }
}
