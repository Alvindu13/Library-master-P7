package com.library.persistance.svc.contracts;

import com.library.persistance.dao.model.Book;

import java.util.List;

public interface BookSvc {

    List<Book> findAllByGenre(String genre);
    //List<Book> findAllByBorrower(Long borrowerId);



}
