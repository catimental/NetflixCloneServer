package io.github.catimental.NetflixCloneServer.repository;

import io.github.catimental.NetflixCloneServer.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);;
    Optional<Member> findById(Long id);
    Optional<Member> findByLoginId(String longinId);
    List<Member> findAll();
}
