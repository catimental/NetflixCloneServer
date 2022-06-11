package io.github.catimental.NetflixCloneServer.repository.movie;

import io.github.catimental.NetflixCloneServer.domain.LikeMovieId;
import io.github.catimental.NetflixCloneServer.domain.LikeMovie;
import io.github.catimental.NetflixCloneServer.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeMovieRepository extends JpaRepository<LikeMovie, LikeMovieId> {

    @Query("select l.likeMovieId.movieId from LikeMovie l where l.likeMovieId.memberId = :memberId")
    List<Long> findByMemberId(@Param(value = "memberId") long memberId);
}
