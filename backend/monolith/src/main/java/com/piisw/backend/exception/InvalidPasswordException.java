package com.piisw.backend.exception;

public class InvalidPasswordException extends BadRequestException {
    public InvalidPasswordException() {
        super("error.invalidPassword");
    }
}