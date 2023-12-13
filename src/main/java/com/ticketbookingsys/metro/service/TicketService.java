package com.ticketbookingsys.metro.service;

import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.entity.Ticket;
import com.ticketbookingsys.metro.exception.AlreadyUsedTicket;
import com.ticketbookingsys.metro.exception.NotFoundException;
import com.ticketbookingsys.metro.exception.NotUsedTicket;
import com.ticketbookingsys.metro.repository.StationRepository;
import com.ticketbookingsys.metro.repository.TicketRepository;
import com.ticketbookingsys.metro.request.CreateTicketRequest;
import com.ticketbookingsys.metro.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final AppUtils appUtils;
    private final TicketRepository ticketRepository;

    public Ticket generateTicket(CreateTicketRequest createTicketRequest) throws Exception {
        log.info("inside {} service", TicketService.class);
        Station sourceStation = appUtils.findStation(createTicketRequest.getSource());
        Station destinationStation = appUtils.findStation(createTicketRequest.getDestination());
        Ticket ticket = Ticket.builder()
                .source(createTicketRequest.getSource())
                .destination(createTicketRequest.getDestination())
                .price(Math.abs(sourceStation.getPrice() - destinationStation.getPrice()))
                .localDateTime(LocalDateTime.now())
                .entry(false)
                .exit(false)
                .build();
        return ticketRepository.save(ticket);
    }

    public void updateEntry(String ticketId) throws Exception {
        log.info("inside {} service", TicketService.class);
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        ticketOptional.ifPresentOrElse(
                (ticket) -> {
                    if(ticket.isEntry()) {
                        throw new AlreadyUsedTicket("ticket id " + ticketId + " is already used!");
                    } else {
                        ticket.setEntry(true);
                        ticketRepository.save(ticket);
                    }
                    log.info("Updated ticket with id : {}", ticketId);
                },
                () -> {
                    throw new NotFoundException("ticket id " + ticketId + " doesn't exists!");
                }
        );
    }

    public void updateExit(String ticketId) throws Exception {
        log.info("inside {} service", TicketService.class);
        Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
        ticketOptional.ifPresentOrElse(
                (ticket) -> {
                    if(!ticket.isEntry()) {
                        throw new NotUsedTicket("ticket id " + ticketId + " is not entered yet!");
                    }
                    if(ticket.isExit()) {
                        throw new AlreadyUsedTicket("ticket id " + ticketId + " is already used!");
                    } else {
                        ticket.setExit(true);
                        ticketRepository.save(ticket);
                    }
                    log.info("Updated ticket with id : {}", ticketId);
                },
                () -> {
                    throw new NotFoundException("ticket id " + ticketId + " doesn't exists!");
                }
        );
    }

}


/*
* maxTime and averageTime of Memory and CPU Utilisation => core, lineage, unity, items
* Create and Update Lots and Pallets
*
* */