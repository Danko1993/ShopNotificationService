package com.daniel.kosk.microservices.notificationservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationSendingService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendActivationLink(String to, String link) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("kosekshop@gmail.com");
        helper.setTo(to);
        helper.setSubject("Account Activation");
        helper.setText(link + "< < Click here to activate Your account");
        mailSender.send(message);
    }
    public void sendUpdateConfirmation(String to, String dataName,String oldValue,String newValue) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("kosekshop@gmail.com");
        helper.setTo(to);
        helper.setSubject("Your " + dataName + " has been updated}");
        helper.setText("Your " + dataName + " has been updated form: "+ oldValue +" to: " + newValue+".", true);
        mailSender.send(message);
    }
}
