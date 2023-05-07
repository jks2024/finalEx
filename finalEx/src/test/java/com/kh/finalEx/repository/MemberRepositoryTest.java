package com.kh.finalEx.repository;

import com.kh.finalEx.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 가입 테스트")
    public void regMemberTest() {
        for(int i = 1; i <= 10; i++) {
            Member member = new Member();
            member.setUserId("JKS2024" + i);
            member.setPassword("SPHB8250");
            member.setName("곰돌이사육사" + i);
            member.setEmail("jks2024@gmail.com");
            member.setJoinTime(LocalDateTime.now());
            memberRepository.save(member);
        }
    }
}