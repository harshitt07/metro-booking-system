package com.ticketbookingsys.metro.exception;

public class AlreadyUsedTicket extends RuntimeException {
    public AlreadyUsedTicket(String message) {
        super(message);
    }
}
