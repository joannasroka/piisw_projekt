package com.piisw.backend.service.account_activation;

import com.piisw.backend.builder.user.PassengerBuilder;
import com.piisw.backend.controller.dto.AccountActivationRequest;
import com.piisw.backend.entity.account_activation.VerificationToken;
import com.piisw.backend.entity.user.Passenger;
import com.piisw.backend.exception.InvalidPasswordException;
import com.piisw.backend.exception.TokenNotFoundException;
import com.piisw.backend.repository.UserRepository;
import com.piisw.backend.repository.VerificationTokenRepository;
import com.piisw.backend.service.mail.ActivationEmailService;
import com.piisw.backend.validator.CustomPasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.piisw.backend.entity.account_activation.AccountStatus.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VerificationTokenServiceTest {

    @InjectMocks
    VerificationTokenService verificationTokenService;

    @Mock
    VerificationTokenRepository verificationTokenRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    CustomPasswordValidator customPasswordValidator;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    ActivationEmailService activationEmailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCreatingNewToken_thenShouldReturnToken() {
        // given
        Passenger user = PassengerBuilder.get().build();
        String token = "1234567";
        VerificationToken verificationToken = new VerificationToken(user, token);

        when(verificationTokenRepository.findByUser(user)).thenReturn(Optional.empty());
        when(verificationTokenRepository.save(verificationToken)).thenReturn(verificationToken);

        // when
        VerificationToken savedToken = verificationTokenService.create(user);

        // then
        assertNotNull(savedToken);
        assertEquals(user, savedToken.getUser());
        assertEquals(verificationToken.getToken(), savedToken.getToken());
        assertEquals(verificationToken.getExpiryDate(), savedToken.getExpiryDate());

        verify(verificationTokenRepository, times(1)).findByUser(user);
        verify(verificationTokenRepository, times(1)).save(verificationToken);
    }

    @Test
    void whenValidatingValidToken_thenShouldDoNothing() {
        // given
        Passenger user = PassengerBuilder.get().build();
        String token = "1234567";
        VerificationToken verificationToken = new VerificationToken(user, token);

        when(verificationTokenRepository.findByTokenAndExpiryDateGreaterThanEqual(ArgumentMatchers.any(String.class), ArgumentMatchers.any(LocalDateTime.class)))
                .thenReturn(Optional.of(verificationToken));

        // when
        verificationTokenService.validateToken(verificationToken.getToken());

        // then
        verify(verificationTokenRepository, times(1)).findByTokenAndExpiryDateGreaterThanEqual(ArgumentMatchers.any(String.class), ArgumentMatchers.any(LocalDateTime.class));
    }

    @Test
    void whenValidatingNonValidToken_thenShouldThrowException() {
        // given
        Passenger user = PassengerBuilder.get().build();
        String token = "1234567";
        VerificationToken verificationToken = new VerificationToken(user, token);

        LocalDateTime pastDate = LocalDateTime.now().minusHours(1);
        verificationToken.setExpiryDate(pastDate);

        when(verificationTokenRepository.findByTokenAndExpiryDateGreaterThanEqual(ArgumentMatchers.any(String.class), ArgumentMatchers.any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        // when & then
        assertThrows(TokenNotFoundException.class, () -> verificationTokenService.validateToken(verificationToken.getToken()));

        // then
        verify(verificationTokenRepository, times(1)).findByTokenAndExpiryDateGreaterThanEqual(ArgumentMatchers.any(String.class), ArgumentMatchers.any(LocalDateTime.class));
    }

    @Test
    void whenActivatingAccountWithValidData_thenShouldSetPasswordAndStatus() {
        // given
        Passenger user = PassengerBuilder.get().build();
        String token = "1234567";
        VerificationToken verificationToken = new VerificationToken(user, token);

        String password = "newPassword1!";
        String encodedPassword = "$2a$10$RRL84rHscHnZh4djT72vQeSCH1hfZSqwTJmRfko37exL.m/TJ3vOO";

        AccountActivationRequest accountActivation = new AccountActivationRequest(token, password);

        when(verificationTokenRepository.findByTokenAndExpiryDateGreaterThanEqual(ArgumentMatchers.any(String.class), ArgumentMatchers.any(LocalDateTime.class)))
                .thenReturn(Optional.of(verificationToken));
        when(customPasswordValidator.validate(password)).thenReturn(true);
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        // when
        verificationTokenService.activateAccount(accountActivation);

        // then
        assertEquals(encodedPassword, user.getPassword());
        assertEquals(ACTIVE, user.getAccountStatus());

        verify(verificationTokenRepository, times(1)).findByTokenAndExpiryDateGreaterThanEqual(ArgumentMatchers.any(String.class), ArgumentMatchers.any(LocalDateTime.class));
        verify(customPasswordValidator, times(1)).validate(password);
        verify(passwordEncoder, times(1)).encode(password);
        verify(verificationTokenRepository, times(1)).delete(verificationToken);
    }

    @Test
    void whenActivatingAccountWithNonExistingToken_thenShouldThrowException() {
        // given
        String token = "1234567";
        String password = "newPassword1!";

        AccountActivationRequest accountActivation = new AccountActivationRequest(token, password);

        when(verificationTokenRepository.findByTokenAndExpiryDateGreaterThanEqual(ArgumentMatchers.any(String.class), ArgumentMatchers.any(LocalDateTime.class)))
                .thenReturn(Optional.empty());

        // when & then
        assertThrows(TokenNotFoundException.class, () -> verificationTokenService.activateAccount(accountActivation));

        verify(verificationTokenRepository, times(1)).findByTokenAndExpiryDateGreaterThanEqual(ArgumentMatchers.any(String.class), ArgumentMatchers.any(LocalDateTime.class));
    }

    @Test
    void whenActivatingAccountWithInvalidPassword_thenShouldThrowException() {
        // given
        Passenger user = PassengerBuilder.get().build();
        String token = "1234567";
        VerificationToken verificationToken = new VerificationToken(user, token);

        String password = "newpassword";

        AccountActivationRequest accountActivation = new AccountActivationRequest(token, password);

        when(verificationTokenRepository.findByTokenAndExpiryDateGreaterThanEqual(ArgumentMatchers.any(String.class), ArgumentMatchers.any(LocalDateTime.class)))
                .thenReturn(Optional.of(verificationToken));
        when(customPasswordValidator.validate(password)).thenReturn(false);

        // when & then
        assertThrows(InvalidPasswordException.class, () -> verificationTokenService.activateAccount(accountActivation));

        verify(verificationTokenRepository, times(1)).findByTokenAndExpiryDateGreaterThanEqual(ArgumentMatchers.any(String.class), ArgumentMatchers.any(LocalDateTime.class));
        verify(customPasswordValidator, times(1)).validate(password);
    }
}