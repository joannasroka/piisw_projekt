package com.piisw.backend.entity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.piisw.backend.entity.account_activation.AccountStatus.REGISTERED;

@Entity
@Table(name = "passengers")
@DiscriminatorValue("PASSENGER")
@Getter
@Setter
public class Passenger extends User {

    protected Passenger() {
        super(REGISTERED);
    }

    @Override
    public UserRole getRole() {
        return UserRole.PASSENGER;
    }

}
