package com.maple.home.util.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;


@Data
public class RqDto {
    @NotEmpty
    @Size(max = 12)
    @Pattern(regexp = "^[가-힣a-zA-Z]*$")
    private String world;

    @NotEmpty
    private List<String> guilds;
}
