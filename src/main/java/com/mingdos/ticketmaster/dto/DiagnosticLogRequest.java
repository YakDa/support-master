package com.mingdos.ticketmaster.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiagnosticLogRequest {
    @NotBlank
    private String step;

    @NotBlank
    private String result;

    @NotBlank
    private String performedBy;
}
