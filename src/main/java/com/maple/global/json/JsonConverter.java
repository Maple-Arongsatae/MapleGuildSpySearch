package com.maple.global.json;

import com.maple.member.model.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

    public List<?> getJsonArrData(String guildName, String rsData) throws Exception {
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
    }
}
