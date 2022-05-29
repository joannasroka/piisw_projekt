package com.piisw.backend.controller.authentication;


import com.piisw.backend.configuration.security.JwtTokenUtil;
import com.piisw.backend.security.AppUserDetailsService;
import io.jsonwebtoken.impl.DefaultClaims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final AppUserDetailsService appUserDetailsService;

    @PostMapping(value = "/login", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @Operation(description = "Login")
    private ResponseEntity<?> login(@Parameter(name = "username", required = true) @RequestBody String username,
                                    @Parameter(name = "password", required = true) @RequestBody String password) throws Exception {
        authenticate(username, password);

        final UserDetails userDetails = appUserDetailsService
                .loadUserByUsername(username);

        return ResponseEntity.ok(jwtTokenUtil.generateToken(userDetails));
    }

    @PostMapping(value = "/logout")
    @Operation(description = "Logout")
    private void logout() {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

//    @GetMapping(value = "/refreshtoken")
//    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception {
//        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
//
//        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
//        String token = jwtTokenUtil.doGenerateRefreshToken(expectedMap,
//                expectedMap.get("sub").toString());
//        return ResponseEntity.ok(token);
//    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    username, password
                            )
                    );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
