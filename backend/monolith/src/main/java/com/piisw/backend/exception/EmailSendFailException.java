package com.piisw.backend.exception;

public class EmailSendFailException extends RuntimeException {
    public EmailSendFailException(String message) {
        super(message);
    }
}