package com.maple.global.exception.advice;

import com.maple.global.exception.custom.IException;
import com.maple.global.exception.custom.impl.ExceptionImpl;
import com.maple.global.exception.custom.impl.IOExceptionImpl;
import com.maple.global.exception.custom.impl.NullPointExceptionImpl;
import com.maple.global.exception.custom.impl.ParseExceptionImpl;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public enum ExceptionCollection {
    NULL_POINT_EXCEPTION(NullPointerException.class, new NullPointExceptionImpl()),
    PARSE_EXCEPTION(ParseException.class, new ParseExceptionImpl()),
    IO_EXCEPTION(IOException.class, new IOExceptionImpl()),
    EXCEPTION(Exception.class, new ExceptionImpl());


    private final Class<?> exception;
    private final IException iException;

    ExceptionCollection(Class<?> exception, IException ie) {
        this.exception = exception;
        this.iException = ie;
    }

    public static IException getExceptionType(CustomException e) {
        for (ExceptionCollection ex : values()) {
            if (ex.exception.isInstance(e.getException())) {
                return ex.iException;
            }
        }
        return EXCEPTION.iException;
    }
}
