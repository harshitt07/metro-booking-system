package com.ticketbookingsys.metro.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStationRequest {

    @NotBlank(message = "Station Name Can't be NULL!")
    String stationName;

    @Positive(message = "Price should be Positive!")
    Long price;
}
