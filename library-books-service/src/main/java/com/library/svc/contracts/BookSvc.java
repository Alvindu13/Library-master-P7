package com.library.svc.contracts;

import com.library.dao.model.Book;

import java.util.List;

public interface BookSvc {

    List<Book> findAllByGenre(String genre);
    //List<Book> findAllByBorrower(Long borrowerId);



}
