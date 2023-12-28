package com.maple.home.util.dto;

import java.util.List;
import lombok.Data;

@Data
public class RqDto {
    private String world;
    private List<String> guilds;
}
