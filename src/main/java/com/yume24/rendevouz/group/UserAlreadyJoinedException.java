package com.yume24.rendevouz.group;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyJoinedException extends RuntimeException {
    public UserAlreadyJoinedException(String message) {
        super(message);
    }
}
