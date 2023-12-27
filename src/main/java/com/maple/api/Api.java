package com.maple.api;

import com.maple.api.util.AppConfig;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api {
    public String getRequest(String urlString) {
        try {
            String API_KEY = AppConfig.getApiKeyProperty("key");
            URL url = new URL(urlString);

            // HTTP connection 설정
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", API_KEY);

            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if(responseCode == 200) {
                // responseCode 200 정상응답
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                // responseCode 200 이외의 코드가 반환되었을 경우
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();

        } catch (Exception exception) {
            System.out.println(exception);
        }

        throw new IllegalArgumentException("API가 정상작동하지 않았습니다.");
    }
}
