package com.maple.global.function;

import com.maple.global.function.impl.CharacterFunction;
import com.maple.global.function.impl.GuildFunction;
import com.maple.global.function.impl.UnionFunction;
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

    public void profileMaker(String world, List<String> guilds) throws Exception {
        List<Member> guildMembers = new ArrayList<>();
        for (String guild : guilds) {
            guildMembers.addAll((List<Member>)gf.getGuildMembers(guild, world));
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

            if (nicknames.contains(mainCharacterNickname)) {
                member.updateSpy(false);
            }
        }
    }
}
