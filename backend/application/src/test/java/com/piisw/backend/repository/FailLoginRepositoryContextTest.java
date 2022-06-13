package com.piisw.backend.repository;


import com.piisw.backend.builder.authentication.FailedLoginAttemptBuilder;
import com.piisw.backend.entity.authentication.FailedLoginAttempt;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class FailLoginRepositoryContextTest {

    @Autowired
    FailLoginRepository failLoginRepository;

    @Rollback
    @Transactional
    @Test
    @DisplayName("Should clear failed login attempts")
    void shouldClearFailedLoginAttempts() {
        //given
        int lockDurationInMinutes = 60;
        int lockRemoveOffsetInMinutes = 10;
        String email1 = "admin1@mail.com";
        String email2 = "admin2@mail.com";

        //Edge condition: lock duration is precisely equal to lock pass time with offset consideration;
        FailedLoginAttempt failedLoginAttempt1 = FailedLoginAttemptBuilder.get()
                .withEmail(email1)
                .withFailAttempts(2)
                .withLastFailAttempt(LocalDateTime.now().minusMinutes(lockDurationInMinutes + lockRemoveOffsetInMinutes))
                .build();

        //lock duration is slightly exceeding the time that lock is passed with offset consideration
        FailedLoginAttempt failedLoginAttempt2 = FailedLoginAttemptBuilder.get()
                .withEmail(email2)
                .withFailAttempts(5)
                .withLastFailAttempt(LocalDateTime.now().minusMinutes(lockDurationInMinutes + lockRemoveOffsetInMinutes + 1))
                .build();

        failLoginRepository.saveAll(List.of(failedLoginAttempt1, failedLoginAttempt2));

        //when
        failLoginRepository.clearExpiredFailLoginAttempts(lockDurationInMinutes, lockRemoveOffsetInMinutes);

        //then
        assertTrue(failLoginRepository.findByEmail(email1).isEmpty());
        assertTrue(failLoginRepository.findByEmail(email2).isEmpty());
    }

    @Rollback
    @Transactional
    @Test
    @DisplayName("Should not clear failed login attempts")
    void shouldNotClearFailedLoginAttempts() {
        //given
        int lockDurationInMinutes = 60;
        int lockRemoveOffsetInMinutes = 10;
        String email1 = "admin1@mail.com";
        String email2 = "admin2@mail.com";
        String email3 = "admin3@mail.com";

        //Offset + 1 minute to pass lock
        FailedLoginAttempt failedLoginAttempt1 = FailedLoginAttemptBuilder.get()
                .withEmail(email1)
                .withFailAttempts(2)
                .withLastFailAttempt(LocalDateTime.now().minusMinutes(lockDurationInMinutes + lockRemoveOffsetInMinutes - 1))
                .build();

        //Whole lockTimeDuration left to lock expiration
        FailedLoginAttempt failedLoginAttempt2 = FailedLoginAttemptBuilder.get()
                .withEmail(email2)
                .withFailAttempts(5)
                .withLastFailAttempt(LocalDateTime.now())
                .build();

        //LockTimeDuration is expired, but additional offset time is not expired yet
        FailedLoginAttempt failedLoginAttempt3 = FailedLoginAttemptBuilder.get()
                .withEmail(email3)
                .withFailAttempts(5)
                .withLastFailAttempt(LocalDateTime.now().minusMinutes(lockDurationInMinutes))
                .build();

        failLoginRepository.saveAll(List.of(failedLoginAttempt1, failedLoginAttempt2, failedLoginAttempt3));

        //when
        failLoginRepository.clearExpiredFailLoginAttempts(lockDurationInMinutes, lockRemoveOffsetInMinutes);

        //then
        assertTrue(failLoginRepository.findByEmail(email1).isPresent());
        assertTrue(failLoginRepository.findByEmail(email2).isPresent());
        assertTrue(failLoginRepository.findByEmail(email3).isPresent());
    }
}