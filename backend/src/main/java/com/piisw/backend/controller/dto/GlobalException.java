package com.piisw.backend.controller.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GlobalException {

    private final LocalDateTime timestamp;

    private final String message;

    public GlobalException(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }
}

