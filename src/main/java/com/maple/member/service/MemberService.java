package com.maple.member.service;

import com.maple.member.model.Member;
import com.maple.member.repository.MemberRepository;
import com.maple.member.util.dto.MemberDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }
    public void reset() {
        memberRepository.clear();
    }

    public Map<String, List<MemberDto>> getMembers() {
        Map<String, List<Member>> allMembers = memberRepository.getMembers();

        return allMembers.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList())
                ));
    }

    public void updateNicknames() {
        for (Member member : memberRepository.getAllMembers()) {
            memberRepository.getNicknames().add(member.getNickname());
        }
    }

    public List<String> getNicknames() {
        return memberRepository.getNicknames();
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

