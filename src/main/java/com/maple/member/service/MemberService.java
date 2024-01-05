package com.maple.member.service;

import com.maple.api.function.AllFunction;
import com.maple.global.exception.custom.CustomException;
import com.maple.member.model.Member;
import com.maple.member.util.dto.MemberDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final AllFunction allFunction;

    public Map<String, List<MemberDto>> getMembers(String world, List<String> guilds) throws CustomException {
        Map<String, List<Member>> allMembers = allFunction.profileMaker(world, guilds);
        return allMembers.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList())));
    }

    private MemberDto convertToDto(Member member) {
        return MemberDto.builder()
                .nickname(member.getNickname())
                .guild(member.getGuild())
                .mainCharacterNickname(member.getMainCharacterNickname())
                .mainCharacterGuild(member.getMainCharacterGuild())
                .spy(member.isSpy())
                .build();
    }
}

