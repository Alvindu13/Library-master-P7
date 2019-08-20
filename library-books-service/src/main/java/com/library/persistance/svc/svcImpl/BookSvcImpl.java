package com.library.persistance.svc.svcImpl;

import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.model.Book;
import com.library.persistance.dao.repository.BookRepository;
import com.library.persistance.svc.contracts.BookSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class BookSvcImpl implements BookSvc {

    @Autowired
    BookRepository repo;

    @Override
    public List<Book> findAllByGenre(String genre) {
        return null;
    }



    public void reserve(Book book,  AppUser appUser) {

        book.setAvailable(false);
        book.setBorrower(appUser);
        book.setBorrowDate(LocalDateTime.now());
        repo.save(book);
    }


    public void extend(Book book) {

        LocalDateTime ldt = book.getBorrowDate();
        LocalDateTime currentTime = LocalDateTime.now();

        System.out.println(book.toString());

        System.out.println(book.getBorrowDate());

        if(book.getBorrowDate().plus(4, ChronoUnit.WEEKS).isAfter(currentTime)) {
            book.setBorrowDate(ldt != null ? ldt.plus(4, ChronoUnit.WEEKS) : null);
            book.setIsProlongation(true);
            repo.save(book);
        }
        else{
            System.out.println("La date de réservation est postérieure à 4 semaines");
        }
    }

    /*@Override
    public List<Book> findAllByBorrower(Long borrowerId) {
        return repo.findAllByBorrowerId(borrowerId);
    }*/
}
