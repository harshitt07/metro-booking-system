package com.ticketbookingsys.metro.rest;

import com.ticketbookingsys.metro.entity.Ticket;
import com.ticketbookingsys.metro.request.CreateTicketRequest;
import com.ticketbookingsys.metro.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/buyTicket")
    public Ticket buyTickets(@RequestBody CreateTicketRequest createTicketRequest) throws Exception {
        log.info("inside {} controller", TicketController.class);
        return ticketService.generateTicket(createTicketRequest);
    }
}
