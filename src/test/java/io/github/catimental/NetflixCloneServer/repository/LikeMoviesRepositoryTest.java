package io.github.catimental.NetflixCloneServer.repository;

import io.github.catimental.NetflixCloneServer.repository.movie.LikeMovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class LikeMoviesRepositoryTest {
    @Autowired
    LikeMovieRepository likeMoviesRepository;

    @Test
    public void sqlTest() {
        System.out.println(likeMoviesRepository.findAll().toString());
    }
}
