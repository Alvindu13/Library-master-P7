package com.clientui.web.proxies;

import com.clientui.beans.BookBean;
import com.clientui.beans.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "library-consumer", url = "localhost:9005")
public interface MicroserviceConsumerProxy {

        @GetMapping("/books")
        List<BookBean> findBooks();

        @GetMapping("/books/user/{borrowerId}")
        List<BookBean> borrowerBooks(@PathVariable Long borrowerId);

        // Find
        @GetMapping("/users")
        List<UserBean> findAllUsers();

        // Save
        @PostMapping("/users")
        //return 201 instead of 200
        @ResponseStatus(HttpStatus.CREATED)
        UserBean newUser(UserBean userBean);

        // Find
        @GetMapping("/users/{id}")
        UserBean findOneUser(@PathVariable Long id);


        // Find
        @GetMapping("/users/{username}")
        UserBean findByName(@PathVariable String username);

        @PatchMapping("/books/date/{id}")
        BookBean bookReserve(@RequestBody BookBean newBook, @PathVariable Long id);

        @GetMapping("/books/{id}")
        BookBean findOneBook(@PathVariable Long id);


}
