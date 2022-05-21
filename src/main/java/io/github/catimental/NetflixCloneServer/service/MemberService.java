package io.github.catimental.NetflixCloneServer.service;

import io.github.catimental.NetflixCloneServer.domain.Member;
import io.github.catimental.NetflixCloneServer.repository.MemberRepository;
import io.github.catimental.NetflixCloneServer.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long register(Member member) {
        validateDuplicateMember(member); //중복검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByLoginId(member.getLoginId())
                        .ifPresent(member1 -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다");
                        });
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Long id){
        return memberRepository.findById(id);
    }

}