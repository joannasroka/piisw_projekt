package com.piisw.backend.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerResponse {
    @Schema(required = true)
    private Long id;

    @Schema(required = true)
    private String email;

    @Schema(required = true)
    private String firstName;

    @Schema(required = true)
    private String lastName;
}
