package com.cheolhyeon.jpashop.service;

import com.cheolhyeon.jpashop.domain.Member;
import com.cheolhyeon.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("Member's id doesn't duplication");
        }
    }
    /*회원 전체 조회*/
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
    /*회원 단건 조회*/
    public Member findById(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
