package com.library.web.controller;

import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.model.Book;
import com.library.persistance.dao.repository.BookRepository;
import com.library.persistance.svc.contracts.BookSvc;
import com.library.security.AccountService;
import com.library.web.exceptions.BookNotFoundException;
import com.library.web.exceptions.BookUnSupportedFieldPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

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


    /**
     * Renvoie les livres réservés par l'utilisateur connecté
     * @param borrowerId
     * @return
     */
    @GetMapping("/user/{borrowerId}")
    List<Book> findAllByBorrowerId(@PathVariable("borrowerId") Long borrowerId) {
        return repository.findAllByBorrowerId(borrowerId);
    }


    @PatchMapping("/{username}/reserve/{bookId}")
    void reserveBook(@PathVariable("username") String username, @PathVariable("bookId") Long bookId) {
        AppUser currentUser = svcUser.loadUserByUsername(username);
        svc.reserve(repository.findBookById(bookId), currentUser);
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
