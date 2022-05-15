package com.piisw.backend.builder.user;

import com.piisw.backend.entity.user.Passenger;

public class PassengerBuilder {
    private Passenger passenger;

    private PassengerBuilder() {
        passenger = new Passenger("john.smith@mail.com", "John", "Smith");
    }

    public PassengerBuilder withFirstName(String firstName) {
        passenger.setFirstName(firstName);
        return this;
    }

    public PassengerBuilder withLastName(String lastName) {
        passenger.setLastName(lastName);
        return this;
    }

    public PassengerBuilder withEmail(String email) {
        passenger.setEmail(email);
        return this;
    }

    public Passenger build() {
        return passenger;
    }

    public static PassengerBuilder get() {
        return new PassengerBuilder();
    }
}
