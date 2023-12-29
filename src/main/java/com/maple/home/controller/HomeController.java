package com.maple.home.controller;

import com.maple.api.Api;
import com.maple.global.exception.advice.CustomException;
import com.maple.api.function.FunctionService;
import com.maple.home.util.dto.ErrorDto;
import com.maple.home.util.dto.ResponseDTO;
import com.maple.home.util.dto.RqDto;
import com.maple.home.util.dto.RsDto;
import com.maple.member.model.Member;
import com.maple.member.service.MemberService;
import com.maple.member.service.MemberTestService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/maple/guild")
public class HomeController {
    private final MemberService memberService;
    private final MemberTestService memberTestService;
    private final FunctionService fs;

    @PostMapping("/spy")
    public ResponseDTO getSpy(@RequestBody RqDto rqDto) throws CustomException {
        fs.profileMaker(rqDto.getWorld(), rqDto.getGuilds());
        log.info("[HomeController.getSpy] API Count : '" + Api.getCount());
        return RsDto.builder()
                .code(200)
                .world(rqDto.getWorld())
                .guildIndex(rqDto.getGuilds())
                .guilds(memberService.getMembers())
                .build();
    }


    @PostMapping("/spy/test")
    public ResponseDTO getSpyTest(@RequestBody RqDto rqDto) {
        try {
            Map<String, List<Member>> members = memberTestService.getMembers();
            return RsDto.builder()
                    .code(200)
                    .world("리부트")
                    .guildIndex(List.of("새빨간", "새파란", "새까만", "샛노란"))
                    .guilds(members)
                    .build();

        } catch (Exception e) {
            return ErrorDto.builder()
                    .code(500)
                    .msg(e.getMessage())
                    .build();
        }
    }
}
