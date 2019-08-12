package com.library.security;

import com.library.persistance.dao.model.AppRole;
import com.library.persistance.dao.model.AppUser;

public interface AccountService {

    AppUser saveUser(String username, String password, String confirmedPassword);
    AppRole saveRole(AppRole appRole);
    AppUser loadUserByUsername(String username);
    void addRoleToUser(String username, String roleName);

}
