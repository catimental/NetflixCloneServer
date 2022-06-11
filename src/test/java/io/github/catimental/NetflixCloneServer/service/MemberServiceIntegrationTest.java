package io.github.catimental.NetflixCloneServer.service;

import io.github.catimental.NetflixCloneServer.domain.Member;
import io.github.catimental.NetflixCloneServer.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void register() {
        //given
        var member = new Member();
        member.setLoginId("hello4");
        member.setLoginPassword("hello4");
        //when
        var saveId = memberService.register(member);

        //then
        var findMember  = memberService.findMember(saveId).get();
        assertThat(member.getLoginId()).isEqualTo(findMember.getLoginId());
    }

    @Test
    void 회원가입_중복() {
        var member = new Member();
        member.setLoginId("hello4");

        var member2 = new Member();
        member2.setLoginId("hello4");

        memberService.register(member);
//        memberService.register(member2);
        assertThrows(IllegalStateException.class, () -> memberService.register(member2));
    }

}