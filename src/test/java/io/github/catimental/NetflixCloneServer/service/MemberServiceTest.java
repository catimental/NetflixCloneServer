package io.github.catimental.NetflixCloneServer.service;

import io.github.catimental.NetflixCloneServer.domain.Member;
import io.github.catimental.NetflixCloneServer.repository.member.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    void register() {
        //given
        var member = new Member();
        member.setLoginId("hello");

        //when
        var saveId = memberService.register(member);

        //then
        var findMember  = memberService.findMember(saveId).get();
        assertThat(member.getLoginId()).isEqualTo(findMember.getLoginId());
    }

    @Test
    void 회원가입_중복() {
        var member = new Member();
        member.setLoginId("hello");

        var member2 = new Member();
        member2.setLoginId("hello");

        memberService.register(member);
//        memberService.register(member2);
        assertThrows(IllegalStateException.class, () -> memberService.register(member2));
    }

    @Test
    void getMembers() {
    }

    @Test
    void findMember() {
    }
}