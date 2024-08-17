package org.kolis1on.citiesgame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotExpectedWordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String notExpectedWordException(NotExpectedWordException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(SymbolIsNotLetterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String symbolIsNotLetterException(SymbolIsNotLetterException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(WordNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String wordNotFoundException(WordNotFoundException ex) {
        return ex.getMessage();
    }
}