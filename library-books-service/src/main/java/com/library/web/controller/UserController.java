package com.library.web.controller;


import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.repository.AppUserRepository;
import com.library.security.AccountService;
import com.library.security.jwt.DecodeToken;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/appUsers")
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AppUserRepository repository;



    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm){
        return accountService.saveUser(
                userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
    }

    @GetMapping()
    List<AppUser> findAllUsers() {
        return repository.findAll();
    }


    @GetMapping("/selected/")
    AppUser findUserByUsername(HttpServletRequest request) {
        DecodeToken decodeToken = new DecodeToken();
        String username = decodeToken.decodeUsername(request);
        return repository.findByUsername(username);
    }

}

@Data
class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;
}
