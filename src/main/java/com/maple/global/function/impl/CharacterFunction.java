package com.maple.global.function.impl;

import com.maple.global.function.IFunction;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CharacterFunction implements IFunction {
    public String getCharacterOcid(String nickName) throws IOException {
        String url = "https://open.api.nexon.com/maplestory/v1/id?"
                + "character_name=" + URLEncoder.encode(nickName, StandardCharsets.UTF_8);

        String rsData = api.getRequest(url);
        return jsonConverter.getJsonObjData(rsData, "ocid");
    }

    public String getCharacterGuild(String ocid) throws IOException {
        String url = "https://open.api.nexon.com/maplestory/v1/character/basic?"
                + "ocid=" + ocid
                + "&date=" + day;

        String rsData = api.getRequest(url);
        return jsonConverter.getJsonObjData(rsData, "character_guild_name");
    }
}