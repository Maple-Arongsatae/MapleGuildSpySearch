package com.maple.member.util.dto;

import com.maple.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String nickname;
    private String guild;
    private String mainCharacterNickname;
    private String mainCharacterGuild;
    private boolean spy = true;
    
    public MemberDto(Member member) {
        this.nickname = member.getNickname();
        this.guild = member.getGuild();
        this.mainCharacterNickname = member.getMainCharacterNickname();
        this.mainCharacterGuild = member.getMainCharacterGuild();
        this.spy = member.isSpy();
    }
}
