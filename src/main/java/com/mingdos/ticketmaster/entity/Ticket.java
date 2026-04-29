package com.mingdos.ticketmaster.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String deviceId;

    @NotBlank
    private String customerId;

    @NotBlank
    @Size(max = 500)
    private String issue;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    private String assignedAgentId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime resolvedAt;

    @OneToMany(mappedBy = "ticketId", cascade = CascadeType.ALL)
    private List<DiagnosticLog> diagnosticLogs = new ArrayList<>();
}
