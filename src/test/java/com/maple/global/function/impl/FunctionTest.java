package com.maple.global.function.impl;

import com.maple.api.function.impl.CharacterFunction;
import com.maple.api.function.impl.GuildFunction;
import com.maple.api.function.impl.UnionFunction;
import com.maple.global.exception.custom.CustomException;
import com.maple.member.model.Member;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FunctionTest {
    private static final GuildFunction GF = new GuildFunction();
    private static final UnionFunction UF = new UnionFunction();
    private static final CharacterFunction CF = new CharacterFunction();


    @Test
    @DisplayName("[길드] 새빨간 전체 길드원 조회")
    void t1() throws CustomException {
        String guild = "새빨간";
        String world = "리부트";

        List<Member> guildMembers = (List<Member>) GF.getGuildMembers(guild, world);

        for (Member member : guildMembers) {
            System.out.println(member);
        }
    }

    @Test
    @DisplayName("[캐릭터] 김태섭 캐릭터 ocid 조회")
    void t2() throws CustomException {
        String nickname = "김태섭";

        String characterOcid = CF.getCharacterOcid(nickname);

        System.out.println("'" + nickname + "'의 ocid : " + characterOcid);
    }

    @Test
    @DisplayName("[캐릭터] 김태섭 캐릭터 길드 조회")
    void t3() throws CustomException {
        String nickname = "김태섭";

        String characterOcid = CF.getCharacterOcid(nickname);
        String characterGuild = CF.getCharacterGuild(characterOcid);

        System.out.println("'" + nickname + "' 의 길드 : " + characterGuild);
    }

    @Test
    @DisplayName("[유니온] 김태섭의 최대 레벨 캐릭터 이름 조회")
    void t4()  throws CustomException{
        String nickname = "김태섭";
        String world = "리부트";
        String result = "썹포터";

        String characterOcid = CF.getCharacterOcid(nickname);
        String mainCharacterNickname = UF.mainCharacterNickname(characterOcid, world);

        System.out.println("mainCharacterNickname = " + mainCharacterNickname);
    }

}