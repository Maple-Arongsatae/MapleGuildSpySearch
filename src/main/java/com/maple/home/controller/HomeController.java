package com.maple.home.controller;

import com.maple.global.exception.custom.CustomException;
import com.maple.home.util.dto.ResponseDTO;
import com.maple.home.util.dto.RqDto;
import com.maple.home.util.dto.RsDto;
import com.maple.home.util.validate.ListDuplicateValidator;
import com.maple.member.service.MemberService;
import com.maple.member.util.dto.MemberDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private static final String LOG_HOME = "[" + HomeController.class.getName() + "]";

    @PostMapping("/spy")
    public ResponseDTO getSpy(@Valid @RequestBody RqDto rqDto) throws CustomException {
        log.info(LOG_HOME + " : 길드 조회 " + createStartLog(rqDto));
        Map<String, List<MemberDto>> members = memberService.getMembers(rqDto.getWorld(), rqDto.getGuilds());

        return RsDto.builder()
                .code(200)
                .world(rqDto.getWorld())
                .guildIndex(ListDuplicateValidator.removeDuplicates(rqDto.getGuilds()))
                .guilds(members)
                .build();
    }

    private String createStartLog(RqDto rqDto) {
        StringBuilder msg = new StringBuilder();
        msg.append(":: DataLog world : " + rqDto.getWorld() + " :: guilds [ ");

        for (String server : rqDto.getGuilds()) {
            msg.append(server + " ");
        }

        msg.append("]");
        return msg.toString();
    }
}