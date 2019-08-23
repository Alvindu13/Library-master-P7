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
    BookRepository bookRepository;

    /**
     * The App user repository.
     */
    @Autowired
    AppUserRepository appUserRepository;


    /**
     * This method enable to reserve a book
     * @param book selected book
     * @param username of user connected
     */
    public void reserve(Book book, String username) {

        book.setAvailable(false);
        book.setBorrower(appUserRepository.findByUsername(username));
        book.setBorrowDate(LocalDate.now());
        bookRepository.save(book);
    }


    /**
     * Enable to extend one reservation of 4 weeks
     * @param book book selected to extend it reservation
     */
    public void extend(Book book) {

        LocalDate ldt = book.getBorrowDate();

        // ajoute 4 semaines de délai à la réservation si l'indicateur de prolongation est sur false et si
        Objects.requireNonNull(book.getIsProlongation(), "Le paramètre prolongation est nulle dans la db");
        if (!book.getIsProlongation()) {
            book.setBorrowDate(ldt != null ? ldt.plus(4, ChronoUnit.WEEKS) : null);
            book.setIsProlongation(true);
            bookRepository.save(book);
        } else {
            logger.info("La réservation a déjà été prolongée");

        }
    }

    /**
     * Search by keyword
     * @param keyword
     * @return
     */
    @Override
    public List<Book> findAllByNameContains(String keyword) {
        return bookRepository.findAllByNameContains(keyword);
    }

    /**
     * Find all books of db
     * @return
     */
    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Count books in db
     * @param name
     * @return
     */
    @Override
    public Long countByName(String name) {
        return bookRepository.countByName(name);
    }

    /**
     * Find books by parameters
     * @param username of user connected
     * @return
     */
    @Override
    public List<Book> findAllByBorrowerUsername(String username) {
        return bookRepository.findAllByBorrowerUsername(username);
    }

    /**
     * Find book by book_id
     * @param bookId
     * @return
     */
    @Override
    public Book findBookById(Long bookId) {
        return bookRepository.findBookById(bookId);
    }

}


