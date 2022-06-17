package com.piisw.backend.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponse {
    @Schema(required = true)
    private UUID globalId;

    @Schema(required = true)
    private String email;

    @Schema(required = true)
    private String firstName;

    @Schema(required = true)
    private String lastName;
}
