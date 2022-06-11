package io.github.catimental.NetflixCloneServer;

import io.github.catimental.NetflixCloneServer.repository.member.MemberRepository;
import io.github.catimental.NetflixCloneServer.repository.movie.LikeMovieRepository;
import io.github.catimental.NetflixCloneServer.service.LikeMovieService;
import io.github.catimental.NetflixCloneServer.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final LikeMovieRepository likeMovieRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository, LikeMovieRepository likeMovieRepository) {
        this.memberRepository = memberRepository;
        this.likeMovieRepository = likeMovieRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public LikeMovieService likeMovieService() {
        return new LikeMovieService(likeMovieRepository);
    }



}
