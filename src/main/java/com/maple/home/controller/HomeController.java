package com.maple.home.controller;

import com.maple.member.service.MemberService;
import com.maple.member.util.dto.MemberDto;
import com.maple.member.service.MemberTestService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/maple/guild")
@AllArgsConstructor
public class HomeController {
    private final MemberService memberService;
    private final MemberTestService memberTestService;

    @GetMapping("/spy")
    public ResponseEntity<?> getSpy() {
        try {
            Map<String, List<MemberDto>> members = memberService.getMembers();
            return ResponseEntity.ok(members);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("조회 중 문제가 발생하였습니다.");
        }
    }


    @GetMapping("/spy/test")

    public ResponseEntity<?> getSpyTest() {
        try {
            Map<String, List<MemberDto>> members = memberTestService.getMembers();
            return ResponseEntity.ok(members);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("조회 중 문제가 발생하였습니다.");
        }
    }
}
