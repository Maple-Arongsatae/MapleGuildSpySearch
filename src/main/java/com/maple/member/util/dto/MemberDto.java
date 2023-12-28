package com.maple.member.util.dto;

import com.maple.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class MemberDto {
    private String nickname;
    private String guild;
    private String mainCharacterNickname;
    private String mainCharacterGuild;
    private boolean spy;

}
