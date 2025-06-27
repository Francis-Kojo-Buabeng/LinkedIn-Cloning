package com.LinkedIn.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.Map;

@ControllerAdvice
public class ErrorsHandlingController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> errorMessage.append(": ").append(error.getDefaultMessage()).append("; ")
        );
        return ResponseEntity.badRequest().body(Map.of("message", errorMessage.toString()));
    }


//    To catch Resources not found in the backend
    @ExceptionHandler (NoResourceFoundException.class)
    public ResponseEntity<Map<String ,String>> handleNOResoureceFoundException (NoResourceFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
    }

//    To catch database errors
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String ,String>> handleDataAccessException (DataAccessException e){
        if (e.getMessage().contains("Duplicate entry")) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already exists, please try another one"));
        }
       return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
    }

//    Handles all exceptions not catch explicitly
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String ,String>> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
    }
}
