package com.maple.global.exception.advice;

import lombok.Data;

@Data
public class CustomException extends Exception{
    private Exception exception;

    public CustomException(Exception e) {
        if (e == null) {
            this.exception = new Exception();
            return;
        }

        this.exception = e;
    }
}
