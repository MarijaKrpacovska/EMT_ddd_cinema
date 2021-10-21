package com.example.movie.config;

import com.example.movie.domain.models.Movie;
import com.example.movie.domain.repositories.MovieRepository;
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
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class initData {

    private final MovieRepository movieRepository;

//    @PostConstruct
//    public void initData() {
//        ScheduledMovie sm = new ScheduledMovie(10, new MovieTime(10,10),new MovieTime(10,10));
//        ScheduledMovie sm1 = new ScheduledMovie(10, new MovieTime(10,10),new MovieTime(10,10));
//        ScheduledMovie sm2 = new ScheduledMovie(10,new MovieTime(10,10),new MovieTime(10,10));
//        Set<ScheduledMovie> scheduledMovies = new HashSet<>();
//        scheduledMovies.add(sm);
//        scheduledMovies.add(sm1);
//        //new Money(Currency.MKD,10),
//        Movie m1 = Movie.build("movie",new MovieLength(10, UnitOfTime.min), Genre.action, Instant.now(),"desc",scheduledMovies,new Money(Currency.MKD,4),"url");
//
//        Set<ScheduledMovie> scheduledMovies1 = new HashSet<>();
//        scheduledMovies.add(sm2);
//        Movie m2 = Movie.build("movie1",new MovieLength(10, UnitOfTime.min), Genre.action, Instant.now(),"desc",scheduledMovies1,new Money(Currency.MKD,4),"url");
//        if (movieRepository.findAll().isEmpty()) {
//            movieRepository.save(m1);
//            movieRepository.save(m2);
//        }
//    }
}
