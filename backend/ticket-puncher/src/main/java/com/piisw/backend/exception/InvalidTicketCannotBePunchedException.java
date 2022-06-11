package com.piisw.backend.exception;

public class InvalidTicketCannotBePunchedException extends BadRequestException {
    public InvalidTicketCannotBePunchedException() {
        super("error.invalidTicketCannotBePunched");
    }
}

