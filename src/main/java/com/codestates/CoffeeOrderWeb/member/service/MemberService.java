package com.codestates.CoffeeOrderWeb.member.service;

import com.codestates.CoffeeOrderWeb.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    public Member createMember(Member member) {
        Member createdMember = member;
        createdMember.setMemberId(1);
        return createdMember;
    }

    public Member updateMember(Member member) {
        Member updatedMember = member;
        updatedMember.setEmail("hgd@gmail.com");
        return updatedMember;
    }

    public Member findMember(long memberId) {
        Member foundMember = new Member(memberId, "hgd@gmail.com", "홍길동", "010-1234-5678");
        return foundMember;
    }

    public List<Member> findMembers() {
        List<Member> foundMembers = List.of(
                new Member(1, "hgd@gmail.com", "홍길동", "010-1234-5678"),
                new Member(2, "lml@gmail.com", "이몽룡", "010-1111-2222")
        );
        return foundMembers;
    }

    public void deleteMember(long memberId) {

    }
}
