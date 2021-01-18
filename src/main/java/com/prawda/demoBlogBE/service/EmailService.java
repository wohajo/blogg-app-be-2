package com.prawda.demoBlogBE.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender sender) {
        this.mailSender = sender;
    }

    public void send(String to, String name, String username) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("Blogg app registration");
        String mail = "<div style=\"font-family: Verdana, Geneva, Tahoma, sans-serif; padding: 0; margin: 0;\">\n" +
                "    <div style=\"width: 100%%; color: white; background-color: rgb(123, 33, 197); font-weight: bold; text-align: center; font-size: x-large; padding: 10px;\">Hey, %s! Thank You for registering in our blog.</div>\n" +
                "    <div style=\"width: 100%%; height: 60px; text-align: center; font-weight: bold; font-size: x-large; padding: 10px;\">Your username: %s</div>\n" +
                "    <div style=\"width: 100%%; color: white; background-color: rgb(123, 33, 197); text-align: center; font-weight: bold; font-size: x-large; padding: 10px;\">Happy blogging!</div>\n" +
                "</div>\n";
        String formatted = String.format(mail, name, username);
        mimeMessageHelper.setText(formatted, true);
        mailSender.send(mimeMessage);
    }

}
