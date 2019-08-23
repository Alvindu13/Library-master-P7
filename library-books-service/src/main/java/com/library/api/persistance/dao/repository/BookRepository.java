package com.library.api.persistance.dao.repository;

import com.library.api.persistance.dao.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByBorrowerUsername(String username);

    List<Book> findAllByNameContains(@Param("keyword") String keyword);

    Book findBookById(Long bookId);

    long countByName(String title);


}


