package com.piisw.backend.controller.authentication;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    @PostMapping(value = "/api/login", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @Operation(description = "Login")
    private void login(@Parameter(name = "username", required = true) @RequestBody String username,
                       @Parameter(name = "password", required = true) @RequestBody String password) {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    @PostMapping(value = "/api/logout")
    @Operation(description = "Logout")
    private void logout() {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }
}
