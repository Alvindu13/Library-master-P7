package com.library.persistance.svc.svcImpl;

import com.library.StartBookApplication;
import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.model.Book;
import com.library.persistance.dao.repository.AppUserRepository;
import com.library.persistance.dao.repository.BookRepository;
import com.library.persistance.svc.contracts.BookSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

/**
 * The type Book svc.
 */
@Service
public class BookSvcImpl implements BookSvc {

    private static final Logger logger = LoggerFactory.getLogger(BookSvcImpl.class);


    /**
     * The Repo.
     */
    @Autowired
    BookRepository repo;

    @Autowired
    AppUserRepository appRepo;

    @Override
    public List<Book> findAllByGenre(String genre) {
        return null;
    }


    public void reserve(Book book,  String username) {

        book.setAvailable(false);
        book.setBorrower(appRepo.findByUsername(username));
        book.setBorrowDate(LocalDate.now());
        repo.save(book);
    }


    public void extend(Book book) {

        LocalDate ldt = book.getBorrowDate();
        //LocalDate currentTime = LocalDate.now();

        System.out.println(book.toString());

        System.out.println(book.getBorrowDate());



        // ajoute 4 semaines de délai à la réservation si l'indicateur de prolongation est sur false et si
        Objects.requireNonNull(book.getIsProlongation(), "Le paramètre prolongation est nulle dans la db");
        if(!book.getIsProlongation()) {
            book.setBorrowDate(ldt != null ? ldt.plus(4, ChronoUnit.WEEKS) : null);
            book.setIsProlongation(true);
            repo.save(book);
        }
        else{
            logger.info("La réservation a déjà été prolongée");

        }
    }

    /*@Override
    public List<Book> findAllByBorrower(Long borrowerId) {
        return repo.findAllByBorrowerId(borrowerId);
    }*/
}
