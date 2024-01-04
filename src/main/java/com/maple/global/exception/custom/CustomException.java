package com.maple.global.exception.custom;

import lombok.Data;

@Data
public class CustomException extends Exception{
    private Exception exception;
    private String msg;

    public CustomException(Exception e, String msg) {
        this.exception = e;
        this.msg = msg;
    }
}
