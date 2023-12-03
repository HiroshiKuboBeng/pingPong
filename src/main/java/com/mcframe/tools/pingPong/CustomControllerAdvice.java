package com.mcframe.tools.pingPong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleSystemException(Exception e) {
        return Map.of(
                "status", "500"
                , "error", e.getClass().getName()
                , "message", e.getLocalizedMessage()
        );
    }
}
