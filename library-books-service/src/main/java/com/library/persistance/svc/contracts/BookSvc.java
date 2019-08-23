package com.library.persistance.svc.contracts;

import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.model.Book;

import java.util.List;

public interface BookSvc {


    void reserve(Book book, String username);

    void extend(Book book);

    List<Book> findAllByNameContains(String keyword);

    List<Book> findAllBooks();

    Long countByName(String name);

    List<Book> findAllByBorrowerUsername(String username);

    Book findBookById(Long bookId);
}
