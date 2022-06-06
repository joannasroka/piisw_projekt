package com.piisw.backend.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class AccountActivationRequest {

    @NotBlank(message = "error.cannotBeBlank")
    @Schema(required = true)
    private String token;

    @NotBlank(message = "error.cannotBeBlank")
    @Schema(required = true)
    private String password;
}
