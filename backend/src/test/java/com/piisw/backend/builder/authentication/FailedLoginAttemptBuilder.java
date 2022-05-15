package com.piisw.backend.builder.authentication;

import com.piisw.backend.entity.authentication.FailedLoginAttempt;

import java.time.LocalDateTime;

public class FailedLoginAttemptBuilder {
    private static int maxFailLoginAttempts = 5;

    private FailedLoginAttempt failedLoginAttempt;

    private FailedLoginAttemptBuilder() {
        failedLoginAttempt = new FailedLoginAttempt("john.smith@mail.com", 0, null, false);
    }

    public FailedLoginAttemptBuilder withEmail(String email) {
        failedLoginAttempt.setEmail(email);
        return this;
    }

    public FailedLoginAttemptBuilder withFailAttempts(int failAttempts) {
        failedLoginAttempt.setFailAttempts(failAttempts);
        if (failAttempts >= maxFailLoginAttempts) {
            failedLoginAttempt.setLocked(true);
        }
        return this;
    }

    public FailedLoginAttemptBuilder withLastFailAttempt(LocalDateTime failAttemptDateTime) {
        failedLoginAttempt.setLastFailAttempt(failAttemptDateTime);
        return this;
    }

    public FailedLoginAttempt build() {
        return failedLoginAttempt;
    }

    public static FailedLoginAttemptBuilder get() {
        return new FailedLoginAttemptBuilder();
    }

}
