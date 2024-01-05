package com.maple.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfig {
    private static final Properties PRO = new Properties();

    // spring-boot-configuration-processor
    public static String getApiKeyProperty(String key) throws IOException {
        InputStream in = ApiConfig.class.getClassLoader().getResourceAsStream("config/apikey.properties");
        PRO.load(in);

        return PRO.getProperty(key);
    }
}
