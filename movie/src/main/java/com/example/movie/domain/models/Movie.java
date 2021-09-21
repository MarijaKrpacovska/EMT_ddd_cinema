package com.example.movie.domain.models;

import com.example.movie.domain.valueobjects.Money;
import com.example.movie.domain.valueobjects.MovieLength;
import com.example.movie.domain.valueobjects.MovieTime;
import com.example.movie.domain.valueobjects.UnitOfTime;
import com.example.sharedkernel.domain.base.AbstractEntity;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name="movie")
@Getter
public class Movie extends AbstractEntity<MovieId> {

    private String name;

    private MovieLength movieLength;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Instant publishDate;

    private String description;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ScheduledMovie> scheduledMovies  = new HashSet<>();

    private Movie() {
        super(MovieId.randomId(MovieId.class));
    }
    public Movie(String name, MovieLength movieLength, Genre genre, Instant publishDate, String description) {
        super(MovieId.randomId(MovieId.class));
        this.name = name;
        this.movieLength = movieLength;
        this.genre=genre;
        this.publishDate=publishDate;
        this.description= description;
    }

    public ScheduledMovie addScheduledMovie(@NonNull Money ticketPrice, int capacity, int sales, MovieTime startTime, MovieTime endTime) {
        Objects.requireNonNull(ticketPrice,"ticket price must not be null");
        var scheduledMovie  = new ScheduledMovie(ticketPrice,capacity,sales,startTime,endTime);
        scheduledMovies.add(scheduledMovie);
        return scheduledMovie;
    }

    public ScheduledMovie findScheduledMovie(int hour, int minutes) {
        //Objects.requireNonNull(startTime,"Movie time must not be null");

        MovieTime startTime = new MovieTime(hour,minutes);
        for (ScheduledMovie sm :
                scheduledMovies) {
            if(sm.scheduledFor().equals(startTime)) {
                return sm;
            }
        }
        return null;
    }

    public List<ScheduledMovie> findTimeOptions() {
        List<ScheduledMovie> scheduledMoviesList = new ArrayList<>();
        for (ScheduledMovie sm :
                scheduledMovies) {
            scheduledMoviesList.add(sm);
        }
        return scheduledMoviesList;
    }

    public void deleteScheduledMovie(@NonNull ScheduledMovieId scheduledMovieId) {
        Objects.requireNonNull(scheduledMovieId,"Scheduled movie must not be null");
        scheduledMovies.removeIf(v->v.getId().equals(scheduledMovieId));
    }


}
