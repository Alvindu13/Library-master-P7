package com.library.persistance.svc.contracts;

import com.library.persistance.dao.model.AppUser;

import java.util.List;

public interface AppUserSvc {

    List<AppUser> findAllUsers();

    AppUser findByUsername(String username);
}
