package com.maple.member.repository;

import com.maple.member.model.Member;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MemberRepository {

    private Map<String, List<Member>> allGuilds;
    private List<String> allNicknames;

    public void save(Member member) {
        allGuilds.computeIfAbsent(member.getGuild(), k -> new ArrayList<>());

        for (String key : allGuilds.keySet()) {
            if (member.getGuild().equals(key)) {
                allGuilds.get(key)
                        .add(member);
            }
        }
    }

    public List<Member> getAllMembers() {
        return allGuilds.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<String> getNicknames() {
        return allNicknames;
    }

    public Map<String, List<Member>> getMembers() {
        return allGuilds;
    }

    @PostConstruct
    public void createObj() {
        allGuilds = new HashMap<>();
        allNicknames = new ArrayList<>();
    }
}

