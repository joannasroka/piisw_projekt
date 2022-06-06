package com.piisw.backend.exception;

public class TokenNotFoundException extends NotFoundException {
    public TokenNotFoundException() {
        super("error.tokenNotFound");
    }
}