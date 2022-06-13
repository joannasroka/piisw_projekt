package com.piisw.backend.service.user;

import com.piisw.backend.controller.dto.PassengerRequest;
import com.piisw.backend.controller.dto.PassengerResponse;
import com.piisw.backend.entity.user.Passenger;
import com.piisw.backend.repository.PassengerRepository;
import com.piisw.backend.service.account_activation.AccountActivationService;
import com.piisw.backend.validator.UserEmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PassengerServiceTest {

    @InjectMocks
    private PassengerService passengerService;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private AccountActivationService accountActivationService;

    @Mock
    private UserEmailValidator userEmailValidator;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Should create passenger")
    void shouldCreatePassenger() {
        //given
        String mail = "john@mail.com";
        String firstName = "John";
        String lastName = "Example";
        PassengerRequest passengerRequest = new PassengerRequest(mail, firstName, lastName);
        Passenger passenger = new Passenger(mail, firstName, lastName);
        PassengerResponse expectedPassengerResponse = new PassengerResponse(1L, mail, firstName, lastName);
        BindingResult bindingResult = new BeanPropertyBindingResult(passengerRequest, "passengerRequest");

        doNothing().when(userEmailValidator).validateEmail(passengerRequest.getEmail(), bindingResult);
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        doNothing().when(accountActivationService).sendActivationMail(any(Passenger.class));

        //when
        PassengerResponse actualPassengerResponse = passengerService.create(passengerRequest, bindingResult);

        //then
        assertNotNull(actualPassengerResponse);
        assertEquals(expectedPassengerResponse.getEmail(), actualPassengerResponse.getEmail());
        assertEquals(expectedPassengerResponse.getFirstName(), actualPassengerResponse.getFirstName());
        assertEquals(expectedPassengerResponse.getLastName(), actualPassengerResponse.getLastName());
        verify(passengerRepository, times(1)).save(any(Passenger.class));
    }
}
