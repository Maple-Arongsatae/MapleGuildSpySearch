package com.maple.home.util.validate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class ListDuplicateValidator {
    // 길드 중복값 제거
    public static List<String> removeDuplicates(List<String> list) {
        LinkedHashMap<String, String> set = new LinkedHashMap<>();
        for (String guildName : list) {
            set.put(guildName, guildName);
        }

        return new ArrayList<>(set.values());
    }
}
