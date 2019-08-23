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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;



@RestController
@RequestMapping("/books")
public class BookController {


    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BookSvc bookSvc;

    @Autowired
    private AccountService accountService;


    /**
     * Renvoie des livres qui contiennent le paramètre dans leur nom.
     * @param keyword
     * @return
     */
    @GetMapping("/selected/{keyword}")
    List<Book> findAllByKeyword(@PathVariable("keyword") String keyword) {
        return bookSvc.findAllByNameContains(keyword);
    }

    @GetMapping
    List<Book> getAllBooks() {
        return bookSvc.findAllBooks();
    }

    @GetMapping("/count/{name}")
    Long count(@PathVariable("name") String name) {
        System.out.println(name);
        return bookSvc.countByName(name);
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

        return bookSvc.findAllByBorrowerUsername(username);
    }

    /**
     *
     * @param bookId
     * @param request
     */
    @PatchMapping("/{bookId}/reserve")
    void reserveBook(@PathVariable("bookId") Long bookId, HttpServletRequest request) {
        DecodeToken decodeToken = new DecodeToken();
        String username = decodeToken.decodeUsername(request);
        bookSvc.reserve(bookSvc.findBookById(bookId), username);
    }

    /**
     *
     * @param bookId
     */
    @PatchMapping("/extend/{bookId}")
    void reserveExtend( @PathVariable("bookId") Long bookId) {
        bookSvc.extend(bookSvc.findBookById(bookId));
    }

}

