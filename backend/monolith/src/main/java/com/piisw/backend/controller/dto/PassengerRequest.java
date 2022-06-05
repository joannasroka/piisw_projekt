package com.piisw.backend.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassengerRequest {
    @NotBlank(message = "error.cannotBeBlank")
    @Email(message = "error.invalidEmail")
    @Length(max = 250, message = "error.invalidFieldLength")
    @Schema(required = true)
    private String email;

    @NotBlank(message = "error.cannotBeBlank")
    @Length(min = 1, max = 100, message = "error.invalidFieldLength")
    @Schema(required = true)
    private String firstName;

    @NotBlank(message = "error.cannotBeBlank")
    @Length(min = 1, max = 100, message = "error.invalidFieldLength")
    @Schema(required = true)
    private String lastName;
}
