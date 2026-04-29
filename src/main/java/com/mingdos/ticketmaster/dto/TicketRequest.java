package com.mingdos.ticketmaster.dto;

import com.mingdos.ticketmaster.entity.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketRequest(

        @NotBlank
        String deviceId,

        @NotBlank
        String customerId,

        @NotBlank
        @Size(max = 500)
        String issue,

        @NotNull
        Priority priority

) {
}
