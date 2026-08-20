package com.yume24.rendevouz.group;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchGroupException extends RuntimeException {
    public NoSuchGroupException(String message) {
        super(message);
    }
}
