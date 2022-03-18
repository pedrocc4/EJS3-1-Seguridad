package com.bosonit.backend.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AsignaturaNoEncontrada extends RuntimeException {
    public AsignaturaNoEncontrada(String message) {
        super(message);
    }
}
