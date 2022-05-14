package com.piisw.backend.service.account_activation;

import com.piisw.backend.entity.account_activation.VerificationToken;
import com.piisw.backend.entity.user.User;
import com.piisw.backend.service.mail.ActivationEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountActivationService {
    private final ActivationEmailService activationEmailService;
    private final VerificationTokenService verificationTokenService;

    public void sendActivationMail(User user) {
        VerificationToken verificationToken = verificationTokenService.create(user);

        activationEmailService.sendActivationMail(verificationToken);
    }
}
