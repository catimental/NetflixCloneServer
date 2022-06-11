package io.github.catimental.NetflixCloneServer.controller;

import io.github.catimental.NetflixCloneServer.domain.LikeMovie;
import io.github.catimental.NetflixCloneServer.domain.LikeMovieId;
import io.github.catimental.NetflixCloneServer.dto.LikeDTO;
import io.github.catimental.NetflixCloneServer.service.LikeMovieService;
import io.github.catimental.NetflixCloneServer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LikeMovieController {
    private final LikeMovieService likeMovieService;

    @Autowired
    public LikeMovieController(LikeMovieService likeMovieService) {
        this.likeMovieService = likeMovieService;
    }

    @PostMapping("/likes")
    @ResponseBody
    public List<Long> getLikeMovies(@RequestHeader HttpHeaders headers) throws IOException {
        final var token = headers.get("Authorization").get(0);
        var likes = likeMovieService.getLikeMovies(Long.valueOf(token));
        return likes;
    }

    @PostMapping("/like")
    public void addLikeMovie(@RequestHeader HttpHeaders headers, LikeDTO likeDTO) {
        final var token = Long.parseLong(headers.get("Authorization").get(0));
        var likeMovie = new LikeMovie();
        likeMovie.setLikeMovieId(new LikeMovieId(token, likeDTO.getMovieId()));
        likeMovieService.addLikeMovie(likeMovie);

    }

    @DeleteMapping("/like/{id}")
    public void removeLikeMovie(@RequestHeader HttpHeaders headers, @PathVariable long id){
        final var token = Long.parseLong(headers.get("Authorization").get(0));
        var likeMovie = new LikeMovie();
        likeMovie.setLikeMovieId(new LikeMovieId(token, id));
        likeMovieService.removeLikeMovie(likeMovie);
    }


    @GetMapping("/like/{id}")
    @ResponseBody
    boolean isLikeMovie(@RequestHeader HttpHeaders headers, @PathVariable long id) {
        final var token = Long.parseLong(headers.get("Authorization").get(0));
        var liked = likeMovieService.isLiked(new LikeMovieId(token, id));
        return liked;
    }
}
