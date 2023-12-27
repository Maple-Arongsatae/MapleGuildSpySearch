package com.maple.member.model;

import lombok.Getter;

@Getter
public class Member {
    private String nickname;
    private String guild;
    private String mainCharacterNickname;
    private String mainCharacterGuild;
    private boolean spy = true;

    public void addNickname(String nickname) {
        this.nickname = nickname;
    }

    public void addMainNickname(String nickname) {
        this.mainCharacterNickname = nickname;
    }

    public void addMainCharterGuild(String guild) {
        this.mainCharacterGuild = guild;
    }

    public void addGuild(String guild) {
        this.guild = guild;
    }

    public void updateSpy(boolean status) {
        this.spy = status;
    }
}
