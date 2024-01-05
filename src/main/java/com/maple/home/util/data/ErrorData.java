package com.maple.home.util.data;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ErrorData implements ResponseData {
    private long code;
    private String msg;
}
