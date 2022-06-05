package com.piisw.backend.repository;

import com.piisw.backend.entity.account_activation.AccountStatus;
import com.piisw.backend.entity.user.User;
import com.piisw.backend.exception.DatabaseEntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndAccountStatus(String email, AccountStatus accountStatus);

    boolean existsByEmailIgnoreCase(String email);

    default User getById(Long id) {
        return findById(id).orElseThrow(DatabaseEntityNotFoundException::new);
    }

    Optional<User> findByEmailIgnoreCase(String email);

    default User getByEmailIgnoreCase(String email) {
        return findByEmailIgnoreCase(email).orElseThrow(DatabaseEntityNotFoundException::new);
    }

    List<User> findAllByAccountStatus(AccountStatus accountStatus);
}
