package com.maple.global.exception.advice;

import com.maple.home.util.dto.ResponseDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseDTO exceptionAdapterHandler(CustomException e) {
        return ExceptionCollection.getExceptionType(e).exceptionPoint(e);
    }
}
