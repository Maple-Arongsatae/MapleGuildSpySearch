package com.maple.api.function.impl;

import com.maple.global.exception.advice.CustomException;
import com.maple.api.function.IFunction;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CharacterFunction implements IFunction {

    /**
     * nickname을 통해 캐릭터 고유 ocid 조회
     * @param nickName
     * @return ocid(String)
     * @throws CustomException
     */
    public String getCharacterOcid(String nickName) throws CustomException {
        String url = "https://open.api.nexon.com/maplestory/v1/id?"
                + "character_name=" + URLEncoder.encode(nickName, StandardCharsets.UTF_8);

        String rsData = api.getRequest(url);
        return jsonConverter.getJsonObjData(rsData, "ocid");
    }

    /**
     * ocid를 통해 캐릭터 길드 조회
     * @param ocid
     * @return guild(String)
     * @throws CustomException
     */
    public String getCharacterGuild(String ocid) throws CustomException {
        String url = "https://open.api.nexon.com/maplestory/v1/character/basic?"
                + "ocid=" + ocid
                + "&date=" + day;

        String rsData = api.getRequest(url);
        return jsonConverter.getJsonObjData(rsData, "character_guild_name");
    }
}
