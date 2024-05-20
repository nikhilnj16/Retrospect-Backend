package com.backend.Retrospect.user.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    @Autowired
    JavaMailSender mailSender;


    public void sendEmail(String toEmail, String subject, String body) {
        if (toEmail == null || subject == null || body == null) {
            System.out.println("Email cannot be sent: One or more parameters are null.");
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jawaharbhaskaran@gmail.com");
        message.setTo(toEmail);
        System.out.println(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail sent successfully");
    }

//
//    public void sendEmail(String toEmail,String subject,String body)
//    {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("madarhuss12345@gmail.com");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//        mailSender.send(message);
//        System.out.println("Mail sent successfullly");
//    }


}
