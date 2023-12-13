package com.ticketbookingsys.metro.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequest {

    @NotNull(message = "source can't be empty")
    private String source;

    @NotNull(message = "destination can't be empty")
    private String destination;
}
