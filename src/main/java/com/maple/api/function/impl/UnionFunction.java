package com.maple.api.function.impl;

import com.maple.global.exception.custom.CustomException;
import com.maple.api.function.IFunction;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UnionFunction implements IFunction {
    private static final String CHECK_DATA = "확인필요";


    /**
     * ocid를통해 월드에 있는 최고 레벨 캐릭터 닉네임 조회
     * @param ocid
     * @param world
     * @return nickname(String)
     * @throws CustomException (확인 되지 않는 경우)
     */
    public String mainCharacterNickname(String ocid, String world) throws CustomException {
        if (ocid.equals(CHECK_DATA)) {
            return CHECK_DATA;
        }

        String url = "https://open.api.nexon.com/maplestory/v1/ranking/union?"
                + "date=" + day
                + "&world_name=" + URLEncoder.encode(world, StandardCharsets.UTF_8)
                + "&ocid=" + URLEncoder.encode(ocid, StandardCharsets.UTF_8);

        String rsData = api.getRequest(url);

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(rsData);
            JSONArray array = (JSONArray) jsonObject.get("ranking");

            for (int i = 0; i < array.size(); i++) {
                JSONObject findJsonDataOne = (JSONObject) array.get(i);
                if (findJsonDataOne.get("world_name").equals(world)) {
                    return findJsonDataOne.get("character_name").toString();
                }
            }

        } catch (Exception e) {
            log.info("[UnionFunction.mainCharacterNickname] exception : " + e.getMessage());
            return CHECK_DATA;
        }
        return CHECK_DATA;
    }
}
