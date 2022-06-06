package com.piisw.backend.builder.user;

import com.piisw.backend.controller.dto.PassengerRequest;

public class PassengerRequestBuilder {
    private PassengerRequest passengerRequest;

    private PassengerRequestBuilder() {
        passengerRequest = new PassengerRequest();
        passengerRequest.setFirstName("John");
        passengerRequest.setLastName("Smith");
        passengerRequest.setEmail("john.smith@mail.com");
    }

    public PassengerRequestBuilder withFirstName(String firstName) {
        passengerRequest.setFirstName(firstName);
        return this;
    }

    public PassengerRequestBuilder withLastName(String lastName) {
        passengerRequest.setLastName(lastName);
        return this;
    }

    public PassengerRequestBuilder withEmail(String email) {
        passengerRequest.setEmail(email);
        return this;
    }

    public PassengerRequest build() {
        return passengerRequest;
    }

    public static PassengerRequestBuilder get() {
        return new PassengerRequestBuilder();
    }
}
