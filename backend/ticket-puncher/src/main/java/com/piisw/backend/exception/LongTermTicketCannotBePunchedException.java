package com.piisw.backend.exception;

public class LongTermTicketCannotBePunchedException extends BadRequestException {
    public LongTermTicketCannotBePunchedException() {
        super("error.longTermTicketCannotBePunched");
    }
}

