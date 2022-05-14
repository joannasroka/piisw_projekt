package com.piisw.backend.controller.dto;

import com.piisw.backend.entity.account_activation.AccountStatus;
import com.piisw.backend.entity.user.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    @Schema(required = true)
    private Long id;

    @Schema(required = true)
    private String email;

    @Schema(required = true)
    private String firstName;

    @Schema(required = true)
    private String lastName;

    @Schema(required = true)
    private AccountStatus accountStatus;

    @Schema(required = true)
    private UserRole role;
}
