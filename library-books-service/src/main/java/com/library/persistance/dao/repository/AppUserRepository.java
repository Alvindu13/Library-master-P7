package com.library.persistance.dao.repository;

import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(@Param("username") String username);
}
