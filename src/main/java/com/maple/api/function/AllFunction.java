package com.maple.api.function;

import com.maple.global.exception.custom.CustomException;
import com.maple.api.function.impl.CharacterFunction;
import com.maple.api.function.impl.GuildFunction;
import com.maple.api.function.impl.UnionFunction;
import com.maple.home.util.validate.ListDuplicateValidator;
import com.maple.member.model.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AllFunction {
    private final UnionFunction uf;
    private final CharacterFunction cf;
    private final GuildFunction gf;

    /**
     * 이중길드 조회 결과 데이터 생성
     *
     * @param world
     * @param guilds
     * @throws CustomException
     */
    public Map<String, List<Member>> profileMaker(String world, List<String> guilds) throws CustomException {
        Map<String, List<Member>> allGuilds = new HashMap<>();
        List<String> allNicknames = new ArrayList<>();

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

            allGuilds.computeIfAbsent(member.getGuild(), k -> new ArrayList<>());

            for (String key : allGuilds.keySet()) {
                if (member.getGuild().equals(key)) {
                    allGuilds.get(key)
                            .add(member);
                }
            }
        }

        List<Member> allMember = allGuilds.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        for (Member member : allMember) {
            allNicknames.add(member.getNickname());
        }

        for (Member member : guildMembers) {
            String mainCharacterNickname = member.getMainCharacterNickname();

            if (allNicknames.contains(mainCharacterNickname) || mainCharacterNickname.equals("확인필요")) {
                member.updateSpy(false);
            }
        }

        return allGuilds;
    }
}
