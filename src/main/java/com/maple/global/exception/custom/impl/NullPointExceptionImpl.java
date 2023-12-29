package com.maple.global.exception.custom.impl;

import com.maple.global.exception.advice.CustomException;
import com.maple.global.exception.custom.IException;
import com.maple.home.util.dto.ErrorDto;
import com.maple.home.util.dto.ResponseDTO;

public class NullPointExceptionImpl implements IException {
    @Override
    public ResponseDTO exceptionPoint(CustomException e) {
        return ErrorDto.builder()
                .code(400)
                .msg("데이터 조회 오류")
                .build();
    }
}
