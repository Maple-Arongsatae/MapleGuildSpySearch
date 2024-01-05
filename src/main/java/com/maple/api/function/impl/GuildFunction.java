package com.maple.api.function.impl;

import com.maple.api.function.IFunction;
import com.maple.global.exception.custom.CustomException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GuildFunction implements IFunction {
    /**
     * 길드 고유 ocid 조회
     *
     * @param guildName
     * @param world
     * @return guild_ocid(String)
     * @throws CustomException
     */
    private String getGuildOcid(String guildName, String world) throws CustomException {
        try {
            String url = "https://open.api.nexon.com/maplestory/v1/guild/id?"
                    + "guild_name=" + URLEncoder.encode(guildName, StandardCharsets.UTF_8)
                    + "&world_name=" + URLEncoder.encode(world, StandardCharsets.UTF_8);

            String rsData = api.getRequest(url);
            return jsonConverter.getJsonObjData(rsData, "oguild_id");
        } catch (Exception e) {
            throw new CustomException(e, 404, "월드(" + world + ") 및 길드(" + guildName + ")가 조회 되지 않습니다. 다시 확인해 주세요.");
        }
    }

    /**
     * 길드원 목록 조회 요청
     *
     * @param guildName
     * @param world
     * @return guild_members(List < ? >)
     * @throws CustomException
     */
    public List<?> getGuildMembers(String guildName, String world) throws CustomException {
        String ocid = getGuildOcid(guildName, world);
        String url = "https://open.api.nexon.com/maplestory/v1/guild/basic?"
                + "oguild_id=" + ocid
                + "&date=" + day;

        String rsData = api.getRequest(url);
        return jsonConverter.getJsonArrData(guildName, rsData);
    }
}
