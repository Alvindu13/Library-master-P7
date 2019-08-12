package com.library.persistance.dao.repository;

import com.library.persistance.dao.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {

    //List<Book> findAllByBorrowerId(Long borrowerId);
}


