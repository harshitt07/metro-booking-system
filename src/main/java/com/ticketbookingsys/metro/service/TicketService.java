package com.ticketbookingsys.metro.service;

import com.ticketbookingsys.metro.entity.Station;
import com.ticketbookingsys.metro.entity.Ticket;
import com.ticketbookingsys.metro.repository.StationRepository;
import com.ticketbookingsys.metro.repository.TicketRepository;
import com.ticketbookingsys.metro.request.CreateTicketRequest;
import com.ticketbookingsys.metro.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
                .build();
        return ticketRepository.save(ticket);
    }

}
