package com.maple.global.exception.custom;

import lombok.Data;

@Data
public class CustomException extends Exception {
    private Exception exception;
    private long code;
    private String msg;

    public CustomException(Exception e, long code, String msg) {
        this.code = code;
        this.exception = e;
        this.msg = msg;
    }
}
