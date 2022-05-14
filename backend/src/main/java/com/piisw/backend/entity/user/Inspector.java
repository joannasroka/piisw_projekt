package com.piisw.backend.entity.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import static com.piisw.backend.entity.account_activation.AccountStatus.REGISTERED;

@Entity
@Table(name = "inspectors")
@DiscriminatorValue("INSPECTOR")
@Getter
@Setter
public class Inspector extends User {

    protected Inspector() {
        super(REGISTERED);
    }

    public Inspector(String email, String firstName, String lastName) {
        super(email, null, firstName, lastName, REGISTERED);
    }

    @Override
    public UserRole getRole() {
        return UserRole.INSPECTOR;
    }
}
