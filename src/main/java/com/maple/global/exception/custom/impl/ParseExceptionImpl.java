package com.maple.global.exception.custom.impl;

import com.maple.global.exception.advice.CustomException;
import com.maple.global.exception.custom.IException;
import com.maple.home.util.dto.ErrorDto;
import com.maple.home.util.dto.ResponseDTO;

public class ParseExceptionImpl implements IException {
    @Override
    public ResponseDTO exceptionPoint(CustomException e) {
        return ErrorDto.builder()
                .code(500)
                .msg("JSON Arr 처리 오류")
                .build();
    }
}
