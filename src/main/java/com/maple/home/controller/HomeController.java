package com.maple.home.controller;

import com.maple.api.Api;
import com.maple.global.function.FunctionService;
import com.maple.home.util.dto.ErrorDto;
import com.maple.home.util.dto.RqDto;
import com.maple.home.util.dto.RsDto;
import com.maple.member.model.Member;
import com.maple.member.service.MemberService;
import com.maple.member.service.MemberTestService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/maple/guild")
@AllArgsConstructor
public class HomeController {
    private final MemberService memberService;
    private final MemberTestService memberTestService;
    private final FunctionService fs;

    @PostMapping("/spy")
    public ResponseEntity<?> getSpy(@RequestBody RqDto rqDto) {
        try {
            fs.profileMaker(rqDto.getWorld(), rqDto.getGuilds());
            return ResponseEntity.ok(RsDto.builder()
                    .code(200)
                    .world(rqDto.getWorld())
                    .guildIndex(rqDto.getGuilds())
                    .guilds(memberService.getMembers())
                    .build());

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorDto.builder()
                            .code(417)
                            .msg("API Key 문제 발생"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorDto.builder()
                            .code(500)
                            .msg(e.getMessage()));
        }finally {
            log.info("[HomeController.getSpy] API 사용 수 : '" + Api.getCount() + "' 회");
        }
    }


    @PostMapping("/spy/test")
    public ResponseEntity<?> getSpyTest(@RequestBody RqDto rqDto) {
        log.info("world : " + rqDto.getWorld());

        try {
            Map<String, List<Member>> members = memberTestService.getMembers();
            return ResponseEntity.ok(RsDto.builder()
                    .code(200)
                    .world("리부트")
                    .guildIndex(List.of("새빨간", "새파란", "새까만", "샛노란"))
                    .guilds(members)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("There was a problem with the test data.");
        }
    }
}
