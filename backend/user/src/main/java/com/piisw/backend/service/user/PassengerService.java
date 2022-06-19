package com.piisw.backend.service.user;

import com.piisw.backend.controller.dto.PassengerRequest;
import com.piisw.backend.controller.dto.PassengerResponse;
import com.piisw.backend.entity.user.Passenger;
import com.piisw.backend.repository.PassengerRepository;
import com.piisw.backend.service.account_activation.AccountActivationService;
import com.piisw.backend.validator.UserEmailValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final UserEmailValidator emailValidator;
    private final AccountActivationService accountActivationService;

    public PassengerResponse create(PassengerRequest passengerRequest, Errors errors) {
        emailValidator.validateEmail(passengerRequest.getEmail(), errors);

        Passenger passenger = new Passenger(passengerRequest.getEmail(), passengerRequest.getFirstName(), passengerRequest.getLastName());
        passenger = passengerRepository.save(passenger);

        accountActivationService.sendActivationMail(passenger);

        return new PassengerResponse(passenger.getGlobalId(), passenger.getEmail(), passenger.getFirstName(), passenger.getLastName());
    }


}
