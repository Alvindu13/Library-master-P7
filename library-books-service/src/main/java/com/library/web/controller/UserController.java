package com.library.web.controller;

/*import com.library.dao.model.User;
import com.library.dao.repository.UserRepository;
import com.library.svc.contracts.UserSvc;
import com.library.web.exceptions.UserNofFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    UserRepository repository;


    @Autowired
    UserSvc svc;

    // Find
    @GetMapping("/users")
    List<User> findAllUsers() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/users")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }


    // Find
    @GetMapping("/users/{id}")
    User findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNofFoundException(id));
    }

    // Save or update
    @PutMapping("/users/{id}")
    User saveOrUpdate(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setPassword(newUser.getPassword());
                    x.setEmail(newUser.getEmail());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }


    // Find
    @GetMapping("/users/{username}")
    User findByName(@PathVariable String mail) {
        return repository.findUserByEmail(mail);

                //repository.findUserByUsername(username);
    }



}*/
