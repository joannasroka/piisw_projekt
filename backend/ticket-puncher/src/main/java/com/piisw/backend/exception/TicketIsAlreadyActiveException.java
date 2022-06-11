package com.piisw.backend.exception;

public class TicketIsAlreadyActiveException extends BadRequestException {
    public TicketIsAlreadyActiveException() {
        super("error.ticketIsAlreadyActive");
    }
}

