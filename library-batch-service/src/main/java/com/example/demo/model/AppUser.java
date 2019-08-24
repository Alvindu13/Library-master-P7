/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

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
