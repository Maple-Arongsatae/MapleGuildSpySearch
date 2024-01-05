package com.maple.api;

import com.maple.api.util.ApiConfig;
import com.maple.global.exception.custom.CustomException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.Getter;

public class Api {
    @Getter
    private static Long count = 0L;

    /**
     * url을 통해 api 호출 및 결과 값 반환
     * @param urlString
     * @return josnData(String)
     * @throws CustomException
     */
    public String getRequest(String urlString) throws CustomException {
        try {
            count++;
            String API_KEY = ApiConfig.getApiKeyProperty("key");
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-nxopen-api-key", API_KEY);

            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (IOException e) {
            throw new CustomException(e, "API 접근 오류");
        }
    }
}
