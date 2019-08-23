package com.library.api.web.controller;

import com.library.api.persistance.svc.contracts.BookSvc;
import com.library.api.security.AccountService;
import com.library.api.persistance.dao.model.Book;
import com.library.api.persistance.dao.repository.AppUserRepository;
import com.library.api.security.jwt.DecodeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;


@Api( description="API pour des opérations CRUD sur les livres de la bibliothèque.")
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
    @ApiOperation(value = "Récupère les livres qui correspondent au mot clé")
    @GetMapping("/selected/{keyword}")
    List<Book> findAllByKeyword(@PathVariable("keyword") String keyword) {
        return bookSvc.findAllByNameContains(keyword);
    }

    @ApiOperation(value = "Récupère tous les livres")
    @GetMapping
    List<Book> getAllBooks() {
        return bookSvc.findAllBooks();
    }

    @ApiOperation(value = "Compte les livres selon leur titre")
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
    @ApiOperation(value = "Récupère les livres réservés par l'utilisateur connecté")
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
    @ApiOperation(value = "L'utilisateur réserve le livre sélectionné")
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
    @ApiOperation(value = "Permet de prolonger la réserver de 4 semaines. N'est pas possible qu'une foie par réservation")
    @PatchMapping("/extend/{bookId}")
    void reserveExtend( @PathVariable("bookId") Long bookId) {
        bookSvc.extend(bookSvc.findBookById(bookId));
    }

}

