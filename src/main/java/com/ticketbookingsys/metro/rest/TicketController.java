package com.ticketbookingsys.metro.rest;

import com.ticketbookingsys.metro.entity.Ticket;
import com.ticketbookingsys.metro.request.CreateTicketRequest;
import com.ticketbookingsys.metro.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/buyTicket")
    public Ticket buyTickets(@RequestBody CreateTicketRequest createTicketRequest) throws Exception {
        return ticketService.generateTicket(createTicketRequest);
    }
}
