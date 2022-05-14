package com.piisw.backend.security.exception;

import org.springframework.http.HttpStatus;

public interface WithMessageAuthenticationException {
    String getMessage();

    HttpStatus getHttpStatusCode();
}