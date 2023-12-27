package com.maple.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties PRO = new Properties();

    public static String getApiKeyProperty(String key) {
        InputStream in = AppConfig.class.getClassLoader().getResourceAsStream("config/apikey.properties");
        try {
            PRO.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PRO.getProperty(key);
    }
}
