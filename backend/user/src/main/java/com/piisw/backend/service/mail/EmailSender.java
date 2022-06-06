package com.piisw.backend.service.mail;

public interface EmailSender {

    void send(String to, String from, String subject, String text, boolean isHtmlContent);
}