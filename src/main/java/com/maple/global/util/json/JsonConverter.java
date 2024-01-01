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

    /**
     * Json 데이터 중 단건에 대한 데이터 요청
     * @param jsonStr
     * @param jsonColumnName
     * @return
     */
    public String getJsonObjData(String jsonStr, String jsonColumnName) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);
            return jsonObject.get(jsonColumnName).toString();
        } catch (Exception e) {
            return CHECK_DATA;
        }
    }

    /**
     * Json 데이터 중 배열에 대한 데이터 요청
     * @param guildName
     * @param jsonStr
     * @return
     * @throws CustomException
     */
    public List<?> getJsonArrData(String guildName, String jsonStr) throws CustomException {
        try {
            List<Member> members = new ArrayList<>();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);

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
