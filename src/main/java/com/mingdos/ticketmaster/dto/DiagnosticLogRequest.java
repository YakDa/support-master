package com.mingdos.ticketmaster.dto;

import jakarta.validation.constraints.NotBlank;

public record DiagnosticLogRequest(

        @NotBlank
        String step,

        @NotBlank
        String result,

        @NotBlank
        String performedBy

) {
}
