package com.ticketbookingsys.metro.rest;

import com.ticketbookingsys.metro.annotations.CustomLogExecution;
import com.ticketbookingsys.metro.annotations.LogExecutionTime;
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
    @LogExecutionTime
    @CustomLogExecution
    public Ticket buyTickets(@RequestBody CreateTicketRequest createTicketRequest) throws Exception {
        return ticketService.generateTicket(createTicketRequest);
    }

    @PatchMapping("/updateEntry/{ticketId}")
    @LogExecutionTime
    @CustomLogExecution
    public void updateEntry(@PathVariable(name = "ticketId") String ticketId) throws Exception {
        ticketService.updateEntry(ticketId);
    }

    @PatchMapping("/updateExit/{ticketId}")
    @LogExecutionTime
    @CustomLogExecution
    public void updateExit(@PathVariable(name = "ticketId") String ticketId) throws Exception {
        ticketService.updateExit(ticketId);
    }

}
