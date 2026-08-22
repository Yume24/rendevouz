package com.yume24.rendevouz.groupMembership;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyInGroupException extends RuntimeException {
    public UserAlreadyInGroupException(String message) {
        super(message);
    }
}
