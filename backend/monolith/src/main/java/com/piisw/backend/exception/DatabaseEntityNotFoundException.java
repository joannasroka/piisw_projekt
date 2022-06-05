package com.piisw.backend.exception;

public class DatabaseEntityNotFoundException extends NotFoundException {
    public DatabaseEntityNotFoundException() {
        super("error.resourceNotFound");
    }
}