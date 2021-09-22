package com.example.ticketreservation.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.genre.Genre;
import com.example.sharedkernel.domain.time.MovieLength;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Movie implements ValueObject {

    private MovieId movieId;

    private MovieLength movieLength;

    private Genre genre;

    private String name;

    private Instant publishDate;

    private String description;

    private Set<ScheduledMovie> scheduledMovies  = new HashSet<>();

    private Movie() {
        this.movieId=MovieId.randomId(MovieId.class);
        this.name= "";
        this.publishDate = Instant.now();
        this.description = "";
    }

    @JsonCreator
    public Movie(@JsonProperty("id") MovieId movieId,
                 @JsonProperty("name") String name,
                 @JsonProperty("movieLength") MovieLength movieLength,
                 @JsonProperty("genre") Genre genre,
                 @JsonProperty("publishDate") Instant publishDate,
                 @JsonProperty("description") String description,
                 @JsonProperty("scheduledMovies") Set<ScheduledMovie> scheduledMovies) {
        this.movieId = movieId;
        this.name = name;
        this.movieLength = movieLength;
        this.genre=genre;
        this.publishDate=publishDate;
        this.description= description;
        this.scheduledMovies=scheduledMovies;
    }

}
