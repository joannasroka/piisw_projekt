package com.piisw.backend.service.account_activation;

import com.piisw.backend.controller.dto.AccountActivationRequest;
import com.piisw.backend.entity.account_activation.AccountStatus;
import com.piisw.backend.entity.account_activation.VerificationToken;
import com.piisw.backend.entity.user.User;
import com.piisw.backend.exception.InvalidPasswordException;
import com.piisw.backend.exception.TokenNotFoundException;
import com.piisw.backend.repository.UserRepository;
import com.piisw.backend.repository.VerificationTokenRepository;
import com.piisw.backend.service.mail.ActivationEmailService;
import com.piisw.backend.validator.CustomPasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;
    private final CustomPasswordValidator customPasswordValidator;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ActivationEmailService activationEmailService;

    public VerificationToken create(User user) {
        tokenRepository.findByUser(user).ifPresent(tokenRepository::delete);

        String token = generateToken();

        return tokenRepository.save(new VerificationToken(user, token));
    }

    public void validateToken(String token) {
        tokenRepository.findByTokenAndExpiryDateGreaterThanEqual(token, LocalDateTime.now())
                .orElseThrow(TokenNotFoundException::new);
    }

    public void activateAccount(AccountActivationRequest accountActivation) {
        VerificationToken verificationToken = tokenRepository.findByTokenAndExpiryDateGreaterThanEqual(accountActivation.getToken(), LocalDateTime.now())
                .orElseThrow(TokenNotFoundException::new);

        User user = verificationToken.getUser();
        String userPassword = accountActivation.getPassword().trim();

        if (!customPasswordValidator.validate(userPassword)) {
            throw new InvalidPasswordException();
        }

        String encodedPassword = passwordEncoder.encode(userPassword);
        user.setPassword(encodedPassword);
        user.setAccountStatus(AccountStatus.ACTIVE);

        tokenRepository.delete(verificationToken);
    }

    private String generateToken() {
        String token;
        do {
            token = UUID.randomUUID().toString();
        }
        while (tokenRepository.existsByToken(token));

        return token;
    }
}
