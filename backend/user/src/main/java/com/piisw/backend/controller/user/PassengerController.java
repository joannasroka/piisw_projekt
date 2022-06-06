package com.piisw.backend.controller.user;

import com.piisw.backend.controller.BaseController;
import com.piisw.backend.controller.dto.PassengerRequest;
import com.piisw.backend.controller.dto.PassengerResponse;
import com.piisw.backend.service.user.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/passengers")
@RequiredArgsConstructor
public class PassengerController extends BaseController {

    private final PassengerService passengerService;

    @PostMapping
    @ResponseStatus(CREATED)
    public PassengerResponse createPassenger(@RequestBody @Valid PassengerRequest passengerRequest, Errors errors) {
        return passengerService.create(passengerRequest, errors);
    }
}
