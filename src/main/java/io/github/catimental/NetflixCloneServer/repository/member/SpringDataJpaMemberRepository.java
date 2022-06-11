package io.github.catimental.NetflixCloneServer.repository.member;

import io.github.catimental.NetflixCloneServer.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//이렇게하면 구현체를 만들어줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByLoginId(String longinId);
}
