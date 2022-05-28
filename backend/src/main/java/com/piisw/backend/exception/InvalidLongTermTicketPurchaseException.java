package com.piisw.backend.exception;

public class InvalidLongTermTicketPurchaseException extends BadRequestException {
    public InvalidLongTermTicketPurchaseException() {
        super("error.invalidLongTermTicketPurchase");
    }
}
