package com.example.movie.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.genre.Genre;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieLength;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name="movie")
@Getter
public class Movie extends AbstractEntity<MovieId> {

    private String name;

    //Za atributot movieLength, definirav nov tip, MovieLength, so cel da se zapazi seprisutniot jazik
    private MovieLength movieLength;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Instant publishDate;

    private String description;

    private Money ticketPrice;

    private String url;

    private int numberOfTimesScheduled;

//    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
//    private Set<ScheduledMovie> scheduledMovies  = new HashSet<>();

    private Movie() {
        super(MovieId.randomId(MovieId.class));
    }

    public Movie(String name, MovieLength movieLength, Genre genre, Instant publishDate, String description, Money ticketPrice, String url, int numberOfTimesScheduled) {
        super(MovieId.randomId(MovieId.class));
        this.name = name;
        this.movieLength = movieLength;
        this.genre=genre;
        this.publishDate=publishDate;
        this.description= description;
        this.url=url;
        this.ticketPrice=ticketPrice;
        this.numberOfTimesScheduled=numberOfTimesScheduled;
    }

    public static Movie build(String name, MovieLength movieLength, Genre genre, Instant publishDate, String description, Money ticketPrice, String url, int numberOfTimesScheduled) {// Set<ScheduledMovie> scheduledMovieSet
        Movie m = new Movie();
        m.name=name;
        m.movieLength=movieLength;
        m.genre=genre;
        m.publishDate=publishDate;
        m.description=description;
     //   m.scheduledMovies=scheduledMovieSet;
        m.url=url;
        m.ticketPrice=ticketPrice;
        m.numberOfTimesScheduled=numberOfTimesScheduled;
        return m;
    }

    public void addTimesScheduled() {
        this.numberOfTimesScheduled = this.numberOfTimesScheduled + 1;
    }

    public void decreaseTimesScheduled() {
        this.numberOfTimesScheduled = this.numberOfTimesScheduled - 1;
    }


//    //sluzhi za zakazhuvanje na prikazhuvanje na film
//    public ScheduledMovie addScheduledMovie(MovieTime startTime, MovieTime endTime) {
//       // Objects.requireNonNull(ticketPrice,"ticket price must not be null");
//        //brojot na sales sekogash ke bide 0, bidejki ovoj scheduledMovie tukushto e dodaden
//        var scheduledMovie  = new ScheduledMovie(0,startTime,endTime);
//        scheduledMovies.add(scheduledMovie);
//        return scheduledMovie;
//    }
//
//    //sluzhi za otkazhuvanje na prikazhuvanje na film
//    public void deleteScheduledMovie(@NonNull ScheduledMovieId scheduledMovieId) {
//        Objects.requireNonNull(scheduledMovieId,"Scheduled movie must not be null");
//        scheduledMovies.removeIf(v->v.getId().equals(scheduledMovieId));
//    }
//
//
//    //sluzhi za zgolemuvanje na prodazhbite za edno prikazhuvanje na film.
//    public ScheduledMovie increaseSales(MovieTime movieTime) {
//        //Objects.requireNonNull(startTime,"Movie time must not be null");
//        ScheduledMovie scheduledMovie = findScheduledMovie(movieTime.getHour(),movieTime.getMinutes());
//        scheduledMovie.addSales();
//        return scheduledMovie;
//    }
//
//    //sluzhi za namaluvanje na prodazhbite za edno prikazhuvanje na film.
//    public ScheduledMovie decreaseSales(MovieTime movieTime) {
//        //Objects.requireNonNull(startTime,"Movie time must not be null");
//        ScheduledMovie scheduledMovie = findScheduledMovie(movieTime.getHour(),movieTime.getMinutes());
//        scheduledMovie.removeSales();
//        return scheduledMovie;
//    }
//
//    public List<ScheduledMovie> findTimeOptions() {
//        List<ScheduledMovie> scheduledMoviesList = new ArrayList<>();
//        for (ScheduledMovie sm :
//                scheduledMovies) {
//            scheduledMoviesList.add(sm);
//        }
//        return scheduledMoviesList;
//    }
//
//
//    //sluzhi za naogjanje na konkretno prikazhuvanje na ovoj film (vo odredeno vreme)
//    public ScheduledMovie findScheduledMovie(int hour, int minutes) {
//
//        MovieTime startTime = new MovieTime(hour,minutes);
//        for (ScheduledMovie sm :
//                scheduledMovies) {
//            if(sm.scheduledFor().equals(startTime)) {
//                return sm;
//            }
//        }
//        return null;
//    }


}
