package com.library;

import com.library.persistance.dao.model.AppRole;
import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.model.Book;
import com.library.persistance.dao.repository.BookRepository;
import com.library.security.AccountService;
import com.library.web.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootApplication
public class StartBookApplication {


    @Autowired
    private RepositoryRestConfiguration restConfiguration;

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartBookApplication.class, args);
    }


    @Bean
    CommandLineRunner start(AccountService accountService, BookRepository bookSvc){

        restConfiguration.exposeIdsFor(AppUser.class);
        restConfiguration.exposeIdsFor(AppRole.class);
        restConfiguration.exposeIdsFor(Book.class);


        return args -> {
            accountService.saveRole((new AppRole(null, "USER")));
            accountService.saveRole((new AppRole(null, "ADMIN")));

            Stream.of("alcaraz.jeremie@hotmail.fr", "alvin.mysterio@gmail.com", "user3@gmail.com", "admin@gmail.com").forEach(un->{
                accountService.saveUser(un, "1234", "1234");
            });
            accountService.addRoleToUser("admin@gmail.com", "ADMIN");


            bookSvc.save(new Book(
                    1L, "A Guide to the Bodhisattva Way of Life", "Santideva", 12.99,
                    "Aventure", false,
                    null, null, null,
                    accountService.loadUserByUsername("alcaraz.jeremie@hotmail.fr")));
            bookSvc.save(new Book(2L, "Titanic", "Caprio", 8.99,
                    "Drama", true,
                    null, "unknow.png", null,
                    accountService.loadUserByUsername("alcaraz.jeremie@hotmail.fr")));
            bookSvc.save(new Book(3L, "Titanic", "Rose", 4.99,
                    "Drama", true,
                    null, "unknow.png",
                    LocalDate.of(2019, 8, 6),
                    accountService.loadUserByUsername("alcaraz.jeremie@hotmail.fr")));

            bookSvc.save(new Book(
                    4L, "A Guide to the Bodhisattva Way of Life", "Santideva", 12.99,
                    "Aventure", false,
                    null, null, LocalDate.now(),
                    accountService.loadUserByUsername("alvin.mysterio@gmail.com")));

        };


    }


    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }

    /* run this only on profile 'demo', avoid run this in test
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            repository.save(new Book("A Guide to the Bodhisattva Way of Life", "Santideva", new BigDecimal("15.41")));
            repository.save(new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", new BigDecimal("9.69")));
            repository.save(new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", new BigDecimal("47.99")));
        };
    }*/


}