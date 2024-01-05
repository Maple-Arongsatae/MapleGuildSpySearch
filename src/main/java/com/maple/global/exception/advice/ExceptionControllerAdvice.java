package com.maple.global.exception.advice;

import com.maple.global.exception.custom.CustomException;
import com.maple.home.util.data.ErrorData;
import com.maple.home.util.data.ResponseData;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final String CLASS_LOG = "[ExceptionControllerAdvice] : ";

    @ExceptionHandler(CustomException.class)
    public ResponseData customExceptionHandle(CustomException e) {
        log.info(CLASS_LOG + "ExceptionAdapterHandler Advice 작동 " + e.getMsg());
        return ErrorData.builder()
                .code(e.getCode())
                .msg(e.getMsg())
                .build();
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseData unexpectedTypeExceptionHandler(UnexpectedTypeException e) {
        log.info(CLASS_LOG + "UnexpectedTypeExceptionHandler Advice 작동 : 유효성에 맞지 않은 길드명");
        return ErrorData.builder()
                .code(404)
                .msg("길드명에는 한글 혹은 영문자만 사용 가능합니다.")
                .build();
    }
}
