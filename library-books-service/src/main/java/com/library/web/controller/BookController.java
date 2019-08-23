package com.library.web.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.model.Book;
import com.library.persistance.dao.repository.AppUserRepository;
import com.library.persistance.dao.repository.BookRepository;
import com.library.persistance.svc.contracts.BookSvc;
import com.library.security.AccountService;
import com.library.security.jwt.DecodeToken;
import com.library.security.jwt.SecurityParams;
import com.library.web.exceptions.BookNotFoundException;
import com.library.web.exceptions.BookUnSupportedFieldPatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/books")
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);


    @Autowired
    private BookRepository repository;


    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private BookSvc svc;

    @Autowired
    private AccountService svcUser;


    /**
     * Renvoie des livres qui contiennent le paramètre dans leur nom.
     * @param keyword
     * @return
     */
    @GetMapping("/selected/{keyword}")
    List<Book> findAllByKeyword(@PathVariable("keyword") String keyword) {
        return repository.findAllByNameContains(keyword);
    }

    @GetMapping
    List<Book> getAllBooks() {
        return repository.findAll();
    }

    @GetMapping("/count/{name}")
    Long count(@PathVariable("name") String name) {
        System.out.println(name);
        return repository.countByName(name);
    }
    

    /**
     * Renvoie les livres réservés par l'utilisateur connecté
     * //@param borrowerId
     * @return
     */
    @GetMapping("/user")
    List<Book> findAllByBorrowerId(HttpServletRequest request) {

       DecodeToken decodeToken = new DecodeToken();
       String username = decodeToken.decodeUsername(request);

        System.out.println("username ****************** : " + username);

        return repository.findAllByBorrowerUsername(username);
    }


    @PatchMapping("/{bookId}/reserve")
    void reserveBook(@PathVariable("bookId") Long bookId, HttpServletRequest request) {
        DecodeToken decodeToken = new DecodeToken();
        String username = decodeToken.decodeUsername(request);
        svc.reserve(repository.findBookById(bookId), username);
    }


    @PatchMapping("/extend/{bookId}")
    void reserveExtend( @PathVariable("bookId") Long bookId) {
        svc.extend(repository.findBookById(bookId));
    }

}
/*
    // Find
    @GetMapping()
    List<Book> findAll() {
        return repository.findAll();
    }

    // Save test
    @PostMapping()
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    // Find
    @GetMapping("/{id}")
    Book findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    // Save or update
    @PutMapping("/{id}")
    Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(newBook.getName());
                    x.setAuthor(newBook.getAuthor());
                    x.setPrice(newBook.getPrice());
                    x.setGenre(newBook.getGenre());
                    x.setQuantity(newBook.getQuantity());
                    x.setAvailable(newBook.getAvailable());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    // update author only
    @PatchMapping("/{id}")
    Book patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String author = update.get("author");
                    if (!StringUtils.isEmpty(author)) {
                        x.setAuthor(author);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new BookNotFoundException(id);
                });

    }

    // update dare only
    @PatchMapping("/date/{id}")
    Book bookReserve(@RequestBody Book newBook, @PathVariable Long id) {

        LocalDateTime currentTime = LocalDateTime.now();

        return repository.findById(id)
                .map(x -> {
                    x.setBorrowDate(currentTime);
                    x.setAvailable(false);
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });

    }


    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }


    @GetMapping("/count")
    Count countBooks() {
        return new Count(repository.count());
    }

    /*@GetMapping("/user/{borrowerId}")
    List<Book> borrowerBooks(@PathVariable Long borrowerId) {
        return repository.findAllByBorrowerId(borrowerId);
    }

}*/
