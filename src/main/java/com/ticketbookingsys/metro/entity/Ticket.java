package com.ticketbookingsys.metro.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Document(collection =  "tickets")
@Data // For Getters and Setters
@Builder // For Dynamic Object Creations
public class Ticket {
    @MongoId
    private String id;
    private Long price;
    private String source;
    private String destination;
    private LocalDateTime localDateTime;

}
