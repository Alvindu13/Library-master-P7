package com.library.api.persistance.dao.repository;

import com.library.api.persistance.dao.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(@Param("username") String username);
}
