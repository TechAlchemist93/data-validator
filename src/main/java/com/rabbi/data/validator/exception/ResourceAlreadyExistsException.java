package com.rabbi.data.validator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException{
    static final long serialVersionUID = 1327943942275365628L;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
