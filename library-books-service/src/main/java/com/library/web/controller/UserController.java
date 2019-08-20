package com.library.web.controller;


import com.library.persistance.dao.model.AppUser;
import com.library.persistance.dao.model.Book;
import com.library.persistance.dao.repository.AppUserRepository;
import com.library.security.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/selected/{username}")
    AppUser findAllByKeyword(@PathVariable("username") String username) {
        return repository.findByUsername(username);
    }

}

@Data
class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;
}
