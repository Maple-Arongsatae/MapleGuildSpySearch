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
    private final UnionFunction unionAPi;
    private final CharacterFunction characterApi;
    private final GuildFunction guildApi;

    /**
     * 이중길드 조회 결과 데이터 생성
     *
     * @param world
     * @param guilds
     * @throws CustomException
     */
    public Map<String, List<Member>> profileMaker(String world, List<String> guilds) throws CustomException {
        List<String> uniqueGuilds = ListDuplicateValidator.removeDuplicates(guilds); // 들어온 길드 목록에 중복이 있으면 제거
        Map<String, List<Member>> allGuilds = new HashMap<>(); // k : 길드명 , v : 길드원 목록
        List<Member> guildMembers = new ArrayList<>(); // 모든 길드원 닉네임 목록

        for (String guild : uniqueGuilds) {
            guildMembers.addAll((List<Member>) guildApi.getGuildMembers(guild, world));
        }

        for (Member member : guildMembers) {
            dataSetMember(world, member);
            saveMember(member, allGuilds);
        }

        List<Member> allMember = convertListMembers(allGuilds);
        List<String> allNicknames = createNicknames(allMember);
        searchSpy(guildMembers, allNicknames);
        return allGuilds;

    }

    private void saveMember(Member member, Map<String, List<Member>> allGuilds) {
        allGuilds.computeIfAbsent(member.getGuild(), k -> new ArrayList<>());

        for (String key : allGuilds.keySet()) {
            if (member.getGuild().equals(key)) {
                allGuilds.get(key)
                        .add(member);
            }
        }
    }

    private List<String> createNicknames(List<Member> allMember) {
        List<String> nicknames = new ArrayList<>();
        for (Member member : allMember) {
            nicknames.add(member.getNickname());
        }
        return nicknames;
    }

    private void searchSpy(List<Member> guildMembers, List<String> allNicknames) {
        for (Member member : guildMembers) {
            String mainCharacterNickname = member.getMainCharacterNickname();

            if (allNicknames.contains(mainCharacterNickname) || mainCharacterNickname.equals("확인필요")) {
                member.updateSpy(false);
            }
        }
    }

    private List<Member> convertListMembers(Map<String, List<Member>> allGuilds) {
        List<Member> allMember = allGuilds.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return allMember;
    }

    private void dataSetMember(String world, Member member) throws CustomException {
        String characterOcid = characterApi.getCharacterOcid(member.getNickname());
        String mainCharacterNickname = unionAPi.mainCharacterNickname(characterOcid, world);
        member.addMainNickname(mainCharacterNickname);
        String mainCharacterOcid = characterApi.getCharacterOcid(mainCharacterNickname);
        String mainCharacterGuild = characterApi.getCharacterGuild(mainCharacterOcid);
        member.addMainCharterGuild(mainCharacterGuild);
    }

    private String createErrorLog(String world, List<String> guilds) {
        StringBuilder msg = new StringBuilder();
        msg.append(":: ErrorLog world : " + world + " :: guilds [ ");

        for (String server : guilds) {
            msg.append(server + " ");
        }

        msg.append("]");
        return msg.toString();
    }
}
