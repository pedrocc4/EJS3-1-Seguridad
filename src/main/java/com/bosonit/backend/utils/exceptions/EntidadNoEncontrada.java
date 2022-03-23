package com.bosonit.backend.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadNoEncontrada extends RuntimeException {
    public EntidadNoEncontrada(String message) {
        super(message);
    }
}
