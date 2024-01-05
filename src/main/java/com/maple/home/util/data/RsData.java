package com.maple.home.util.data;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RsData implements ResponseData {
    private long code;
    private String world;
    private List<String> guildIndex;
    private Map<String, ?> guilds;
}
