package com.clientui.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BooktNotFoundException extends RuntimeException {
    public BooktNotFoundException(String message) {super(message);}
}
