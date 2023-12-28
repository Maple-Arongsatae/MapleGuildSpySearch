package com.maple.member.repository;

import org.junit.jupiter.api.Test;

class MemberTestRepositoryTest {
    MemberTestRepository repo = new MemberTestRepository();

    @Test
    void t1() {
        for (String key : repo.getMembers().keySet()) {
            System.out.println(key);
        }
    }
}