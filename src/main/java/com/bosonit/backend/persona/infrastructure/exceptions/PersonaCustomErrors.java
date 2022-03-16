package com.bosonit.backend.persona.infrastructure.exceptions;

import com.bosonit.backend.estudiante.infrastructure.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class PersonaCustomErrors extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnprocesableException.class)
    public final ResponseEntity<ExceptionResponse> handleUnprocesableEntity(UnprocesableException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(PersonaNoEncontrada.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(PersonaNoEncontrada ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(PersonaYaRegistrada.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(PersonaYaRegistrada ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}