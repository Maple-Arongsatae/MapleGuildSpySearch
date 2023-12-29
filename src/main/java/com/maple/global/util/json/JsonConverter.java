package com.maple.global.util.json;

import com.maple.global.exception.advice.CustomException;
import com.maple.member.model.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonConverter {
    private static final String CHECK_DATA = "확인필요";

    public String getJsonObjData(String rsData, String jsonDataName) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(rsData);
            return jsonObject.get(jsonDataName).toString();
        } catch (Exception e) {
            return CHECK_DATA;
        }
    }

    public List<?> getJsonArrData(String guildName, String rsData) throws CustomException {
        try {
            List<Member> members = new ArrayList<>();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(rsData);

            // JSON Array
            JSONArray jsonMembers = (JSONArray) jsonObject.get("guild_member");
            Iterator<String> iterator = jsonMembers.iterator();

            while (iterator.hasNext()) {
                Member member = new Member();
                member.addNickname(iterator.next());
                member.addGuild(guildName);
                members.add(member);
            }
            return members;

        } catch (ParseException e) {
            throw new CustomException(e);

        } catch (NullPointerException e) {
            throw new CustomException(e);
        }

    }
}
