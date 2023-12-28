package com.maple.member.service;

import com.maple.member.model.Member;
import com.maple.member.repository.MemberTestRepository;
import com.maple.member.util.dto.MemberDto;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberTestService {
    private final MemberTestRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }

    public Map<String, List<Member>> getMembers() {
        return memberRepository.getMembers();
    }
}
