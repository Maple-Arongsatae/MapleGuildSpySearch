package com.maple.member.repository;

import com.maple.member.model.Member;
import com.maple.member.util.dto.MemberDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private static final Map<String, List<Member>> ALL_GUILDS = new HashMap<>();
    private static final List<String> ALL_NICKNAMES = new ArrayList<>();

    public void save(Member member) {
        ALL_GUILDS.computeIfAbsent(member.getGuild(), k -> new ArrayList<>());

        for (String key : ALL_GUILDS.keySet()) {
            if (member.getGuild().equals(key)) {
                ALL_GUILDS.get(key)
                        .add(member);
            }
        }
    }

    public List<Member> getAllMembers() {
        return ALL_GUILDS.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<String> getNicknames() {
        return ALL_NICKNAMES;
    }

    public Map<String, List<Member>> getMembers() {
        return ALL_GUILDS;
    }
}

