package com.piisw.backend.configuration.internalization;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import static java.util.Locale.ENGLISH;

@Configuration
public class InternalizationConfig {
    public static final String MAIL_MESSAGE_SOURCE_NAME = "mailMessageSource";
    private static final String MAIL_MESSAGES_PATH = "i18n/mails";

    @Bean(name = MAIL_MESSAGE_SOURCE_NAME)
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(MAIL_MESSAGES_PATH);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(ENGLISH);
        return messageSource;
    }
}
