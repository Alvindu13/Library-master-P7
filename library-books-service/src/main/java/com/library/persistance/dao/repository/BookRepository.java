package com.library.persistance.dao.repository;

import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {

    //@RestResource(path = "/borrowerId")
    List<Book> findAllByBorrowerId(Long borrowerId);


    List<Book> findAllByNameContains(@Param("keyword") String keyword);


    Book findBookById(Long bookId);

    long countByName(String title);

    //List<Book> findAllByBo


}


