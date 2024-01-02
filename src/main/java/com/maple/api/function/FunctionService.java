package com.maple.api.function;

import com.maple.global.exception.advice.CustomException;
import com.maple.api.function.impl.CharacterFunction;
import com.maple.api.function.impl.GuildFunction;
import com.maple.api.function.impl.UnionFunction;
import com.maple.home.util.validate.ListDuplicateValidator;
import com.maple.member.model.Member;
import com.maple.member.service.MemberService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FunctionService {
    private final UnionFunction uf;
    private final CharacterFunction cf;
    private final GuildFunction gf;
    private final MemberService memberService;


    /**
     * 이중길드 조회 결과 데이터 생성
     *
     * @param world
     * @param guilds
     * @throws CustomException
     */
    public void profileMaker(String world, List<String> guilds) throws CustomException {
        memberService.createObj(); // 메모리 DB 초기화

        List<String> uniqueGuilds = ListDuplicateValidator.removeDuplicates(guilds); // 길드 중복 값 제거
        List<Member> guildMembers = new ArrayList<>();

        for (String guild : uniqueGuilds) {
            guildMembers.addAll((List<Member>) gf.getGuildMembers(guild, world));
        }

        for (Member member : guildMembers) {
            String characterOcid = cf.getCharacterOcid(member.getNickname());
            String mainCharacterNickname = uf.mainCharacterNickname(characterOcid, world);
            member.addMainNickname(mainCharacterNickname);
            String mainCharacterOcid = cf.getCharacterOcid(mainCharacterNickname);
            String mainCharacterGuild = cf.getCharacterGuild(mainCharacterOcid);
            member.addMainCharterGuild(mainCharacterGuild);
            memberService.save(member);
        }

        memberService.updateNicknames();
        List<String> nicknames = memberService.getNicknames();

        for (Member member : guildMembers) {
            String mainCharacterNickname = member.getMainCharacterNickname();

            if (nicknames.contains(mainCharacterNickname) || mainCharacterNickname.equals("확인필요")) {
                member.updateSpy(false);
            }
        }
    }
}
