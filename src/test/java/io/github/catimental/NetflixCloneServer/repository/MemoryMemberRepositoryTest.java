package io.github.catimental.NetflixCloneServer.repository;


import io.github.catimental.NetflixCloneServer.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        var member = new Member();
        member.setLoginId("hello");

        repository.save(member);

        var result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByLoginId() {
        var member1 = new Member();
        member1.setLoginId("hello");
        repository.save(member1);

        var member2 = new Member();
        member2.setLoginId("world");
        repository.save(member2);

        assertThat(member1).isEqualTo(repository.findByLoginId("hello").get());
    }

    @Test
    public void findAll() {
        var member1 = new Member();
        member1.setLoginId("hello");
        repository.save(member1);

        var member2 = new Member();
        member2.setLoginId("world");
        repository.save(member2);

        assertThat(repository.findAll().size()).isEqualTo(2);
    }
}
