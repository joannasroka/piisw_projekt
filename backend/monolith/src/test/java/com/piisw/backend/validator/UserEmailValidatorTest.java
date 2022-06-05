package com.piisw.backend.validator;


import com.piisw.backend.builder.user.PassengerRequestBuilder;
import com.piisw.backend.controller.dto.PassengerRequest;
import com.piisw.backend.exception.ValidationException;
import com.piisw.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserEmailValidatorTest {

    @InjectMocks
    UserEmailValidator userEmailValidator;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should throw ValidationException when email is not unique")
    void shouldThrowValidationExceptionWhenEmailIsNotUnique() {
        // given
        PassengerRequest passengerRequest = PassengerRequestBuilder.get().build();
        BindingResult bindingResult = new BeanPropertyBindingResult(passengerRequest, "createHcpDto");

        when(userRepository.existsByEmailIgnoreCase(passengerRequest.getEmail())).thenReturn(true);

        // then
        assertThrows(ValidationException.class, () -> userEmailValidator.validateEmail(passengerRequest.getEmail(), bindingResult));
        verify(userRepository, times(1)).existsByEmailIgnoreCase(passengerRequest.getEmail());
    }

    @Test
    @DisplayName("Should not throw ValidationException when email is unique")
    void shouldNotThrowValidationExceptionWhenEmailIsUnique() {
        // given
        PassengerRequest passengerRequest = PassengerRequestBuilder.get().build();
        BindingResult bindingResult = new BeanPropertyBindingResult(passengerRequest, "createHcpDto");

        when(userRepository.existsByEmailIgnoreCase(passengerRequest.getEmail())).thenReturn(false);

        // then
        assertDoesNotThrow(() -> userEmailValidator.validateEmail(passengerRequest.getEmail(), bindingResult));
        verify(userRepository, times(1)).existsByEmailIgnoreCase(passengerRequest.getEmail());
    }
}