package io.github.catimental.NetflixCloneServer.controller;

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
    public List<Integer> getLikeMovies(@RequestHeader HttpHeaders headers) throws IOException {
        final var token = headers.get("Authorization").get(0);
        var likes = likeMovieService.getLikeMovies(Long.valueOf(token));
        likes.forEach(System.out::println);
        return List.of(1, 2);
    }

    @PostMapping("/like")
    public void addLikeMovie() {

    }


    @GetMapping("/likes/{id}")
    @ResponseBody
    boolean isLikeMovie(@RequestHeader HttpHeaders headers, HttpServletRequest request, @PathVariable String id) {
        return false;
    }
}
