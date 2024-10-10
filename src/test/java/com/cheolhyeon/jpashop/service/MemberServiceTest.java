package com.cheolhyeon.jpashop.service;

import com.cheolhyeon.jpashop.domain.Member;
import com.cheolhyeon.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원 가입")
    public void join() {
        Member kim = Member.builder()
                .name("Kim")
                .build();
        Long saveId = memberService.join(kim);

        assertEquals(kim, memberRepository.findOne(saveId));
    }
    @Test
    @DisplayName("회원 중복 예외")
    public void joinException() {
        //given
        Member kim1 = Member.builder()
                .name("Kim")
                .build();
        Member kim2 = Member.builder()
                .name("Kim")
                .build();
        //when
        memberService.join(kim1);
        assertThrows(IllegalStateException.class, () -> memberService.join(kim2));

        //then
    }
}