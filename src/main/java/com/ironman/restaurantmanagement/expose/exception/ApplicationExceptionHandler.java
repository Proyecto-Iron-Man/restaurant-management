package com.ironman.restaurantmanagement.expose.exception;

import com.ironman.restaurantmanagement.shared.exception.model.ArgumentNotValidError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Spring stereotype annotation
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentNotValidError> handleInvalidArgument(MethodArgumentNotValidException exception) {
        // Variables
        ArgumentNotValidError response = new ArgumentNotValidError();
        Map<String, String> error = new HashMap<>();

        // Process
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    error.put(fieldError.getField(), fieldError.getDefaultMessage());
                });

        response.setMessage("Invalid argument");
        response.setError(error);

        // Result
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
