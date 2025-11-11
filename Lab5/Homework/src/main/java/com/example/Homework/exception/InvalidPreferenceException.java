package com.example.Homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPreferenceException extends RuntimeException {

    public InvalidPreferenceException(String message) { super(message); }

}
