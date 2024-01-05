package com.maple.home.controller;

import com.maple.global.exception.custom.CustomException;
import com.maple.home.util.data.ResponseData;
import com.maple.home.util.data.RqData;
import com.maple.home.util.data.RsData;
import com.maple.home.util.validate.ListDuplicateValidator;
import com.maple.member.service.MemberService;
import com.maple.member.util.dto.MemberDto;
import jakarta.validation.UnexpectedTypeException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private static long count = 0L;

    @PostMapping("/spy")
    public ResponseData getSpy(@Valid @RequestBody RqData rqDto) throws CustomException {
        for (String guild : rqDto.getGuilds()) {
            if (!validateGuildName(guild)) {
                throw new UnexpectedTypeException();
            }
        }

        count++;
        log.info(LOG_HOME + " " + count + " :: 길드 조회 " + createStartLog(rqDto));
        Map<String, List<MemberDto>> members = memberService.getMembers(rqDto.getWorld(), rqDto.getGuilds());

        return RsData.builder()
                .code(200)
                .world(rqDto.getWorld())
                .guildIndex(ListDuplicateValidator.removeDuplicates(rqDto.getGuilds()))
                .guilds(members)
                .build();
    }

    private String createStartLog(RqData rqDto) {
        StringBuilder msg = new StringBuilder();
        msg.append(":: DataLog world : " + rqDto.getWorld() + " :: guilds [ ");

        for (String server : rqDto.getGuilds()) {
            msg.append(server + " ");
        }

        msg.append("]");
        return msg.toString();
    }

    public boolean validateGuildName(String guildName) {
        String pattern = "^[가-힣a-zA-Z_]*$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(guildName);
        return matcher.matches();
    }
}