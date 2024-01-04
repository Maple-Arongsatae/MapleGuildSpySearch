package com.maple.global.exception.advice;

import com.maple.global.exception.custom.CustomException;
import com.maple.home.util.dto.ErrorDto;
import com.maple.home.util.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseDTO exceptionAdapterHandler(CustomException e) {
        log.info("[ExceptionControllerAdvice] : Exception Advice 작동");
        return ErrorDto.builder()
                .code(500)
                .msg(e.getException() + " : " + e.getMsg())
                .build();
    }


}
