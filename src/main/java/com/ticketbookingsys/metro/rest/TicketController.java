package com.ticketbookingsys.metro.rest;

import com.ticketbookingsys.metro.entity.Ticket;
import com.ticketbookingsys.metro.request.CreateTicketRequest;
import com.ticketbookingsys.metro.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/buyTicket")
    public Ticket buyTickets(@RequestBody CreateTicketRequest createTicketRequest) throws Exception {
        log.info("inside {} controller", TicketController.class);
        return ticketService.generateTicket(createTicketRequest);
    }

    @PatchMapping("/updateEntry/{ticketId}")
    public void updateEntry(@PathVariable(name = "ticketId") String ticketId) throws Exception {
        log.info("inside {} controller", StationController.class);
        ticketService.updateEntry(ticketId);
    }

    @PatchMapping("/updateExit/{ticketId}")
    public void updateExit(@PathVariable(name = "ticketId") String ticketId) throws Exception {
        log.info("inside {} controller", StationController.class);
        ticketService.updateExit(ticketId);
    }

}
