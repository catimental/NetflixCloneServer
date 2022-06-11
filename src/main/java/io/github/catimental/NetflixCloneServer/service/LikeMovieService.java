package io.github.catimental.NetflixCloneServer.service;
import io.github.catimental.NetflixCloneServer.domain.LikeMovie;
import io.github.catimental.NetflixCloneServer.domain.LikeMovieId;
import io.github.catimental.NetflixCloneServer.repository.member.MemberRepository;
import io.github.catimental.NetflixCloneServer.repository.movie.LikeMovieRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class LikeMovieService {
    private final LikeMovieRepository likeMovieRepository;

    public LikeMovieService(LikeMovieRepository likeMovieRepository) {
        this.likeMovieRepository = likeMovieRepository;
    }

    public List<Long> getLikeMovies(long memberId) {
        return likeMovieRepository.findByMemberId(memberId);
    }

    public void addLikeMovie(LikeMovie likeMovie) {
        likeMovieRepository.save(likeMovie);
    }

    public void removeLikeMovie(LikeMovie likeMovie) {
        likeMovieRepository.delete(likeMovie);
    }

    public boolean isLiked(LikeMovieId id) {
        return likeMovieRepository.findById(id).isPresent();
    }

}
