package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
public class SimpleMailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private EmailService emailService;

    @RequestMapping("/sendMail")
    public String sendMail() {

        emailService.sendMail("alcaraz.jeremie@hotmail.fr", "Test Subject", "Test mail");

        return "Mail Sent Success!";


        /*MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("alcaraz.jeremie@hotmail.fr");
            helper.setText("Greetings :)");
            helper.setSubject("Mail From Spring Boot");
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "Mail Sent Success!";*/
    }
}
