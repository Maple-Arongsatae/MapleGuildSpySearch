package com.maple.home.util.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ErrorDto {
    private long code;
    private String msg;
}
