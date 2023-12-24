package com.imagefinder.advice;

import com.imagefinder.exception.DomainNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class Handler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DomainNotFoundException.class)
    public List<String> handlerInvalidDomain(DomainNotFoundException ex){
        System.out.println("Excpetion has been caught from the handler");
        return null;
    }
}
