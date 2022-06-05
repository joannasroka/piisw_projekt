package com.piisw.backend.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthorizationConstants {
    public static final String HAS_ROLE_PASSENGER = "hasRole('PASSENGER')";
    public static final String HAS_ROLE_INSPECTOR = "hasRole('INSPECTOR')";
    public static final String HAS_ANY_USER_ROLE = "hasAnyRole('PASSENGER', 'INSPECTOR')";
}
