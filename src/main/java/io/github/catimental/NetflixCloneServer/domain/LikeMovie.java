package io.github.catimental.NetflixCloneServer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "like_movie")
public class LikeMovie {
    @EmbeddedId
    private LikeMovieId likeMovieId;
}
