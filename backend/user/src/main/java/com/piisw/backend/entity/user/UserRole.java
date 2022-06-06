package com.piisw.backend.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {
    PASSENGER("ROLE_PASSENGER"),
    INSPECTOR("ROLE_INSPECTOR");

    private final String authorityRoleName;
}
