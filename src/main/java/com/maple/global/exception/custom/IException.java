package com.maple.global.exception.custom;

import com.maple.global.exception.advice.CustomException;
import com.maple.home.util.dto.ResponseDTO;

public interface IException {
    ResponseDTO exceptionPoint(CustomException e);
}
