package com.maple.global.function.impl;

import com.maple.global.function.IFunction;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GuildFunction implements IFunction {
    public List<?> getGuildMembers(String guildName, String world) throws Exception {
        String ocid = getGuildOcid(guildName, world);
        String url = "https://open.api.nexon.com/maplestory/v1/guild/basic?"
                + "oguild_id=" + ocid
                + "&date=" + day;

        String rsData = api.getRequest(url);
        return jsonConverter.getJsonArrData(guildName, rsData);
    }

    private String getGuildOcid(String guildName, String world) throws IOException {
        String url = "https://open.api.nexon.com/maplestory/v1/guild/id?"
                + "guild_name=" + URLEncoder.encode(guildName, StandardCharsets.UTF_8)
                + "&world_name=" + URLEncoder.encode(world, StandardCharsets.UTF_8);

        String rsData = api.getRequest(url);
        return jsonConverter.getJsonObjData(rsData, "oguild_id");
    }
}
