package io.github.raeperd.realworld.application;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorModel> handleValidationException(MethodArgumentNotValidException ex) {
        final var messages = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(ErrorModel.of(messages));
    }

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<ErrorModel> handleNoSuchElementException() {
        return ResponseEntity.status(NOT_FOUND).body(ErrorModel.of(List.of("not found")));
    }
}
