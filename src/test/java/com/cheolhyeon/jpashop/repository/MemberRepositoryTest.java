package com.cheolhyeon.jpashop.repository;

import com.cheolhyeon.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void doTest() {
        //given
        Member newMember = Member.builder().name("MemberA").build();
        //when
        memberRepository.save(newMember);
        Member findMember = memberRepository.findById(newMember.getId()).get();
        //then
        assertThat(findMember).isEqualTo(newMember);
        assertThat(newMember.getName()).isEqualTo(findMember.getName());
    }
}