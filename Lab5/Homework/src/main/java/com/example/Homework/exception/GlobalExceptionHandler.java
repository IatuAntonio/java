package com.example.Homework.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.Instant;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(InvalidPreferenceException.class)
//    public ResponseEntity<String> handleInvalid(InvalidPreferenceException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                Map.of("timestamp", Instant.now().toString(),
//                       "status", 400,
//                       "error", "Invalid Preference",
//                       "message", e.getMessage()).toString());
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e) {
//        return ResponseEntity.badRequest().body(
//                Map.of("status", 400,
//                        "error", "Validation Error",
//                       "message", e.getBindingResult().toString())
//        );
//    }
//
//}


//package com.example.Homework.controller;

import com.example.Homework.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        "timestamp", Instant.now().toString(),
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", "Not Found",
                        "message", e.getMessage()
                )
        );
    }

    @ExceptionHandler(InvalidPreferenceException.class)
    public ResponseEntity<Map<String, String>> handleInvalid(InvalidPreferenceException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "timestamp", Instant.now().toString(),
                        "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        "error", "Invalid Preference",
                        "message", e.getMessage()
                )
        );
    }

}
