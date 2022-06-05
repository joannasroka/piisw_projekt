package com.piisw.backend.service.authentication;

import com.piisw.backend.builder.authentication.FailedLoginAttemptBuilder;
import com.piisw.backend.entity.authentication.FailedLoginAttempt;
import com.piisw.backend.properties.FailLoginAttemptsProperties;
import com.piisw.backend.repository.FailLoginRepository;
import com.piisw.backend.security.exception.UserLockedAuthenticationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LoginAttemptServiceTest {
    @InjectMocks
    LoginAttemptService loginAttemptService;

    @Mock
    FailLoginAttemptsProperties failLoginAttemptsProperties;

    @Mock
    FailLoginRepository failLoginRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should throw UserLockedException when username is locked")
    void shouldThrowUserLockedExceptionWhenUsernameIsLocked() {
        //given
        FailedLoginAttempt failedLoginAttempt = FailedLoginAttemptBuilder.get()
                .withLastFailAttempt(LocalDateTime.now().plusHours(1))
                .withFailAttempts(5)
                .build();

        when(failLoginRepository.findByEmail(failedLoginAttempt.getEmail())).thenReturn(Optional.of(failedLoginAttempt));
        Mockito.doNothing().when(failLoginRepository).delete(failedLoginAttempt);

        //then
        assertThrows(UserLockedAuthenticationException.class, () -> loginAttemptService.checkIfUsernameLocked(failedLoginAttempt.getEmail()));
    }

    @Test
    @DisplayName("Should delete FailedLoginAttempts and throw no Exception when time is expired")
    void shouldDeleteFailedLoginAttemptsAndThrowNoExceptionWhenTimeIsExpired() {
        //given
        FailedLoginAttempt failedLoginAttempt = FailedLoginAttemptBuilder.get()
                .withLastFailAttempt(LocalDateTime.now().minusHours(3))
                .withFailAttempts(5)
                .build();

        when(failLoginRepository.findByEmail(failedLoginAttempt.getEmail())).thenReturn(Optional.of(failedLoginAttempt));
        Mockito.doNothing().when(failLoginRepository).delete(failedLoginAttempt);

        //then
        assertDoesNotThrow(() -> loginAttemptService.checkIfUsernameLocked(failedLoginAttempt.getEmail()));
        verify(failLoginRepository, times(1)).delete(failedLoginAttempt);
    }

    @Test
    @DisplayName("Should not throw any Exception when there is no FailedLoginAttempt record")
    void shouldNotThrowAnyExceptionWhenNoFailedLoginAttemptRecord() {
        //given
        String email = "email@test.com";

        when(failLoginRepository.findByEmail(email)).thenReturn(Optional.empty());

        //then
        assertDoesNotThrow(() -> loginAttemptService.checkIfUsernameLocked(email));
    }

    @Test
    @DisplayName("Should create new record and throw no Exception when after failed login username is not found ")
    void shouldCreateNewRecordAndThrowNoExceptionAfterFailedLoginWhenUsernameNotFound() {
        //given
        FailedLoginAttempt failedLoginAttempt = FailedLoginAttemptBuilder.get()
                .withLastFailAttempt(LocalDateTime.now())
                .withFailAttempts(1)
                .build();

        when(failLoginRepository.findByEmail(failedLoginAttempt.getEmail())).thenReturn(Optional.empty());
        when(failLoginRepository.save(failedLoginAttempt)).thenReturn(failedLoginAttempt);

        //then
        assertDoesNotThrow(() -> loginAttemptService.loginFailed(failedLoginAttempt.getEmail()));
        verify(failLoginRepository, times(1)).save(failedLoginAttempt);
    }

    @Test
    @DisplayName("Should throw UserLockedException when lock status is true and invoked loginFailed()")
    void shouldThrowUserLockedExceptionAfterFailedLoginWhenLockStatusTrue() {
        //given
        FailedLoginAttempt failedLoginAttempt = FailedLoginAttemptBuilder.get()
                .withLastFailAttempt(LocalDateTime.now())
                .withFailAttempts(5)
                .build();

        when(failLoginRepository.findByEmail(failedLoginAttempt.getEmail())).thenReturn(Optional.of(failedLoginAttempt));

        //then
        assertThrows(UserLockedAuthenticationException.class, () -> loginAttemptService.loginFailed(failedLoginAttempt.getEmail()));
    }

    @Test
    @DisplayName("Should clear FailedLoginAttempts after successful login")
    void shouldClearFailedLoginAttemptsAfterSuccessfulLogin() {
        //given
        FailedLoginAttempt failedLoginAttempt = FailedLoginAttemptBuilder.get()
                .withLastFailAttempt(LocalDateTime.now())
                .withFailAttempts(3)
                .build();

        when(failLoginRepository.findByEmail(failedLoginAttempt.getEmail())).thenReturn(Optional.of(failedLoginAttempt));
        Mockito.doNothing().when(failLoginRepository).delete(failedLoginAttempt);

        //when
        loginAttemptService.clearFailLoginAttemptsAfterSuccessfulLogin(failedLoginAttempt.getEmail());

        //then
        verify(failLoginRepository, times(1)).delete(failedLoginAttempt);
    }
}
