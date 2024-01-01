package com.maple.home.util.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RsDto implements ResponseDTO{
    private long code;
    private String world;
    private List<String> guildIndex;
    private Map<String, ?> guilds;
}
