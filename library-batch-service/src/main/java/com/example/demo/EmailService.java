package com.example.demo;

import com.example.demo.model.Book;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(Book book) {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        var mailMessage = new SimpleMailMessage();

        mailMessage.setTo(book.getBorrower().getUsername());
        mailMessage.setSubject("Bibliothèque réservation - retour" );
        mailMessage.setText("Bonjour, vous avez réservé le livre " + book.getName() + ". Pensez à le ramener avant la date d'échéance : " + book.getBorrowDate().toString());

        javaMailSender.send(mailMessage);
        logger.info("mail avec succès à  : " + book.getBorrower().getUsername());
    }
}
