package com.maple.home.controller;

import com.maple.api.Api;
import com.maple.global.exception.advice.CustomException;
import com.maple.api.function.FunctionService;
import com.maple.home.util.dto.ErrorDto;
import com.maple.home.util.dto.ResponseDTO;
import com.maple.home.util.dto.RqDto;
import com.maple.home.util.dto.RsDto;
import com.maple.home.util.validate.ListDuplicateValidator;
import com.maple.member.model.Member;
import com.maple.member.service.MemberService;
import com.maple.member.service.MemberTestService;
import jakarta.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    private static final String LOG_HOME = "[" + HomeController.class.getName() + "]";

    @PostMapping("/spy")
    public synchronized ResponseDTO getSpy(@Valid @RequestBody RqDto rqDto) throws CustomException {
        LocalDateTime startTime = LocalDateTime.now();
        fs.profileMaker(rqDto.getWorld(), rqDto.getGuilds());
        LocalDateTime endTime = LocalDateTime.now();
        log.info(LOG_HOME + this.getClass()+" 데이터 조회 완료");
        log.info(LOG_HOME + " API Count : '" + Api.getCount());

        Duration duration = Duration.between(startTime, endTime);
        log.info(LOG_HOME + " 데이터 조회 소요 시간 : " + duration.getSeconds()+"s");

        return RsDto.builder()
                .code(200)
                .world(rqDto.getWorld())
                .guildIndex(ListDuplicateValidator.removeDuplicates(rqDto.getGuilds()))
                .guilds(memberService.getMembers())
                .build();
    }


    @GetMapping("/spy/test")
    public ResponseDTO getSpyTest() {
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