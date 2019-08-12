package com.library.persistance.svc.svcImpl;

import com.library.persistance.dao.model.Book;
import com.library.persistance.dao.repository.BookRepository;
import com.library.persistance.svc.contracts.BookSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookSvcImpl implements BookSvc {

    @Autowired
    BookRepository repo;

    @Override
    public List<Book> findAllByGenre(String genre) {
        return null;
    }

    /*@Override
    public List<Book> findAllByBorrower(Long borrowerId) {
        return repo.findAllByBorrowerId(borrowerId);
    }*/
}
