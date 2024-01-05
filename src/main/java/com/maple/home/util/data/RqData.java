package com.maple.home.util.data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;


@Data
public class RqData {
    @NotEmpty
    private String world;

    @NotEmpty
    @Size(max = 12)
    @Pattern(regexp = "^[가-힣a-zA-Z]*$")
    private List<String> guilds;
}
