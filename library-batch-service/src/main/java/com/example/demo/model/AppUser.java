package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.ArrayList;
import java.util.Collection;


@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class AppUser {


    private Long id;

    private String username;


    private String password;

    private boolean actived;

    private Collection<AppRole> roles = new ArrayList<>();

    /*@OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
    private Set<Book> books;*/
}
