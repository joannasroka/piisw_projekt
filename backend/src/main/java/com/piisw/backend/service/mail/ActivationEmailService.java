package com.piisw.backend.service.mail;

import com.piisw.backend.entity.account_activation.VerificationToken;
import com.piisw.backend.entity.user.User;
import com.piisw.backend.mail.AccountActivationMailGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Locale;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySource("classpath:configuration.properties")
public class ActivationEmailService {

    private final EmailSender emailSender;
    private final AccountActivationMailGenerator activationMailGenerator;

    @Autowired
    @Qualifier("mailMessageSource")
    private final MessageSource messageSource;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${configuration.security.accountActivationUrl}")
    private String accountActivationUrl;

    public void sendActivationMail(VerificationToken verificationToken) {
        User user = verificationToken.getUser();
        String body = generateMailBody(user, verificationToken.getExpiryDate(), accountActivationUrl + verificationToken.getToken());
        String topic = messageSource.getMessage("mail.activation.title", null, Locale.ENGLISH);

        emailSender.send(user.getEmail(), from, topic, body, true);
    }

    private String generateMailBody(User user, LocalDateTime linkExpirationTime, String activationLink) {
        String openingName = user.getFirstName() + " " + user.getLastName();

        return activationMailGenerator.generate(openingName, activationLink, linkExpirationTime);
    }
}
