package com.example.demo;

import com.example.demo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


@SpringBootApplication
@EnableScheduling

public class LibraryBatchServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(LibraryBatchServiceApplication.class);


    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        //header
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        /*HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Arrays.asList(new MediaType[] {MediaType.TEXT_XML}));*/

        /*BookList response = restTemplate.getForObject(
                "http://localhost:9005/books",
                BookList.class);
        List<Book> books = response.getBooks();
        System.out.println(response.getBooks().size());

        log.info(books != null ? books.toString() : null);*/

        ResponseEntity<List<Book>> response = restTemplate.exchange(
                "http://localhost:9005/books",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Book>>(){});
        System.out.println(response.getBody());

        List<Book> books = response.getBody();




        /*if (books != null) {
            books.forEach(book -> {
                System.out.print("book: ");
                System.out.println(book);
            });
        }*/


        SpringApplication.run(LibraryBatchServiceApplication.class, args);

    }

}
