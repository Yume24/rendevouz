package com.yume24.rendevouz.group;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GroupDoesNotExistsException extends RuntimeException {
    public GroupDoesNotExistsException(String message) {
        super(message);
    }
}
