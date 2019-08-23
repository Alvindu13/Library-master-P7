package com.library.api.persistance.svc.contracts;

import com.library.api.persistance.dao.model.AppUser;

import java.util.List;

public interface AppUserSvc {

    List<AppUser> findAllUsers();

    AppUser findByUsername(String username);
}
