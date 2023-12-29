package com.maple.api.function;

import com.maple.api.Api;
import com.maple.global.util.json.JsonConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface IFunction {
    JsonConverter jsonConverter = new JsonConverter();
    Api api = new Api();
    String day = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 데이터 기준일 (오늘 - 1일)

}
