package com.piisw.backend.entity.user;

import com.piisw.backend.entity.BaseEntity;
import com.piisw.backend.entity.account_activation.AccountStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public abstract class User extends BaseEntity {

    @Column(name = "email", length = 250, nullable = false, unique = true)
    public String email;

    @Column(name = "password", length = 60)
    public String password;

    @Column(name = "first_name", nullable = false, length = 100)
    public String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    public String lastName;

    @Column(name = "account_status", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    protected User(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public abstract UserRole getRole();
}
