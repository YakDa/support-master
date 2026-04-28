package com.mingdos.ticketmaster.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DiagnosticLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @NotBlank
    private String step;

    @NotBlank
    private String result;

    @NotBlank
    private String performedBy;

    private LocalDateTime timestamp = LocalDateTime.now();
}
