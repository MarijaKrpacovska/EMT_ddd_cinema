package com.example.movie.config;

import com.example.movie.domain.models.Movie;
import com.example.movie.domain.repositories.MovieRepository;
import com.example.movie.domain.valueobjects.Image;
import com.example.movie.domain.valueobjects.ImageType;
import com.example.movie.domain.valueobjects.Rating;
import com.example.movie.domain.valueobjects.Video;
import com.example.sharedkernel.domain.genre.Genre;
import com.example.sharedkernel.domain.money.Currency;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieLength;
import com.example.sharedkernel.domain.time.MovieTime;
import com.example.sharedkernel.domain.time.UnitOfTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class initData {

    private final MovieRepository movieRepository;

    @PostConstruct
    public void initData() {
        Movie m1 = Movie.build("Us",new MovieLength(120,UnitOfTime.min),
                Genre.horror, LocalDate.of(2020,6,2),
                "Us is a 2019 American horror film written and directed by Jordan Peele, starring Lupita Nyong'o, Winston Duke, Elisabeth Moss, and Tim Heidecker. The film follows Adelaide Wilson (Nyong'o) and her family, who are attacked by a group of menacing doppelgängers.",
                new Image("https://collider.com/wp-content/uploads/2018/12/us-movie-poster.jpg", ImageType.poster),
                new Image("https://m.media-amazon.com/images/M/MV5BMjU0MjMyOTc4OF5BMl5BanBnXkFtZTgwMTY0Nzg2NzM@._V1_.jpg", ImageType.advertisement),
                Video.buildVideoWithEmbeddableUrl("https://youtu.be/hNCmb-4oXJA"),
                0,
                new Rating(9.0,30));
        Movie m2 = Movie.build("Black Widow",new MovieLength(100,UnitOfTime.min),
                Genre.action, LocalDate.of(2021,4,2),
                "Natasha Romanoff confronts the darker parts of her ledger when a dangerous conspiracy with ties to her past arises.",
                new Image("https://m.media-amazon.com/images/I/61Fm+N+NncL._AC_SL1008_.jpg", ImageType.poster),
                new Image("https://cdn.mos.cms.futurecdn.net/4L75tkWVDgKtGe7kfoEGP6.jpg", ImageType.advertisement),
                Video.buildVideoWithEmbeddableUrl("https://youtu.be/Fp9pNPdNwjI"),
                0,
                new Rating(8.0,4));
        Movie m3 = Movie.build("Annette",new MovieLength(120,UnitOfTime.min),
                Genre.romance, LocalDate.of(2021,6,6),
                "The plot follows a stand-up comedian (Adam Driver) and his opera singer wife (Marion Cotillard) and how their lives are changed when they have their first child. Simon Helberg and Devyn McDowell also starred.",
                new Image("https://assets.mubicdn.net/images/notebook/post_images/33421/images-w1400.jpg?1625400709", ImageType.poster),
                new Image("https://www.gannett-cdn.com/media/2020/03/23/USATODAY/usatsports/247WallSt.com-247WS-658009-imageForEntry4-th0.jpg?width=2560", ImageType.advertisement),
                Video.buildVideoWithEmbeddableUrl("https://youtu.be/l_EaNpL16SU"),
                0,
                new Rating(10.0,4));
        Movie m4 = Movie.build("Joker",new MovieLength(200,UnitOfTime.min),
                Genre.horror, LocalDate.of(2020,1,2),
                "Set in 1981, it follows Arthur Fleck, a failed clown and stand-up comedian whose descent into insanity and nihilism inspires a violent counter-cultural revolution against the wealthy in a decaying Gotham City.",
                new Image("https://cdn11.bigcommerce.com/s-ydriczk/images/stencil/1280x1280/products/89058/93685/Joker-2019-Final-Style-steps-Poster-buy-original-movie-posters-at-starstills__62518.1572351179.jpg?c=2", ImageType.poster),
                new Image("https://m.media-amazon.com/images/M/MV5BNmZiYzk5MDItMTJiZS00MmQ1LWIxMDgtZTRmOWUwYjc5MDI2XkEyXkFqcGdeQXRyYW5zY29kZS13b3JrZmxvdw@@._V1_.jpg", ImageType.advertisement),
                Video.buildVideoWithEmbeddableUrl("https://youtu.be/zAGVQLHvwOY"),
                0,
                new Rating(9.9,4));
        if (movieRepository.findAll().isEmpty()) {
            movieRepository.saveAll(Arrays.asList(m1,m2,m3,m4));
        }
    }

}
