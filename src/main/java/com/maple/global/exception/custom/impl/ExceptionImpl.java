package com.maple.global.exception.custom.impl;

import com.maple.global.exception.advice.CustomException;
import com.maple.global.exception.custom.IException;
import com.maple.home.util.dto.ErrorDto;
import com.maple.home.util.dto.ResponseDTO;

public class ExceptionImpl implements IException {
    @Override
    public ResponseDTO exceptionPoint(CustomException e) {
        return ErrorDto.builder()
                .code(500)
                .msg(e.getMessage())
                .build();
    }
}
