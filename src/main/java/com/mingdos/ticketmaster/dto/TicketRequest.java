package com.mingdos.ticketmaster.dto;

import com.mingdos.ticketmaster.entity.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketRequest {
    @NotBlank
    private String deviceId;

    @NotBlank
    private String customerId;

    @NotBlank
    @Size(max=500)
    private String issue;

    @NotNull
    private Priority priority;
}
