package com.example.demo;

import com.example.demo.model.Book;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Component
public class ScheduledTasks {

    @Value("${library.api.url}")
    private String apiUrl;


    @Autowired
    private EmailService emailService;

    //Lance la méthode tous les jours à 9h30
    //@Scheduled(cron = "0 * * * * ?")
    @Scheduled(cron = "0 0 0 11 ? * MON-FRI")
    public void batchScheduled() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Book>> response = restTemplate.exchange(
                apiUrl + "/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>(){});
        System.out.println("scheduled work : " + response.getBody());

        List<Book> books = response.getBody();


        if (books != null) {
            books.forEach(book -> {
                System.out.println("actually book : " + book.toString());
                if (book.getBorrowDate() != null) {

                    LocalDate dateStartEmail = book.getBorrowDate().plus(3, ChronoUnit.WEEKS);
                    //LocalDate dateLimit = book.getBorrowDate().plus(4, ChronoUnit.WEEKS);
                    LocalDate currentDate = LocalDate.now();

                    /*System.out.println("Livre : " + book.getId());
                    System.out.println("Date de réservation : " + book.getBorrowDate());
                    System.out.println("Date de lancement des mails : " + dateStartEmail);
                    System.out.println("Date limite pour restituer le livre : " + dateLimit);
                    System.out.println("Date actuelle : " + currentDate);
                    System.out.println("Période : " + ChronoUnit.DAYS.between(currentDate, dateStartEmail));*/
                    /*System.out.println(dateStartEmail.isEqual(currentDate));
                    System.out.println(currentDate.isAfter(dateStartEmail));*/

                    if((ChronoUnit.DAYS.between(currentDate, dateStartEmail) <= 7 && ChronoUnit.DAYS.between(currentDate, dateStartEmail) >= 0)){
                        this.emailService.sendEmail(book);
                    }

                }

            });
        }

    }

}