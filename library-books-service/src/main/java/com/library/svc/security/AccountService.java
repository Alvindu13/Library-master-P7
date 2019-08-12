package com.library.svc.security;

import com.library.dao.model.AppRole;
import com.library.dao.model.AppUser;

public interface AccountService {

    AppUser saveUser(String username, String password, String confirmedPassword);
    AppRole saveRole(AppRole appRole);
    AppUser loadUserByUsername(String username);
    void addRoleToUser(String username, String roleName);

}
