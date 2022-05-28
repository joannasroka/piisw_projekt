package com.piisw.backend.exception;

public class ValidityStartDatePassedException extends BadRequestException {
    public ValidityStartDatePassedException() {
        super("error.validityStartDateCannotBeInThePast");
    }
}
