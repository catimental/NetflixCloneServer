package io.github.catimental.NetflixCloneServer.repository;

import io.github.catimental.NetflixCloneServer.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static AtomicLong sequence = new AtomicLong(0);

    @Override
    public Member save(Member member) {
        member.setId(sequence.getAndIncrement());
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByLoginId(String longinId) {
        return store.values().stream()
                .filter(member -> member.getLoginId().equals(longinId))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
