package com.piisw.backend.controller.account_activation;


import com.piisw.backend.controller.BaseController;
import com.piisw.backend.controller.dto.AccountActivationRequest;
import com.piisw.backend.service.account_activation.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.HEAD;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/tokens")
public class VerificationTokenController extends BaseController {
    private final VerificationTokenService verificationTokenService;

    @RequestMapping(method = HEAD)
    public void validateToken(@RequestParam String token) {
        verificationTokenService.validateToken(token);
    }

    @PostMapping
    public void activateAccount(@RequestBody AccountActivationRequest accountActivation) {
        verificationTokenService.activateAccount(accountActivation);
    }
}
