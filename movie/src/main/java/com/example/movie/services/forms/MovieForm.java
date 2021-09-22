package com.example.movie.services.forms;
import com.example.movie.domain.models.ScheduledMovie;
import com.example.sharedkernel.domain.genre.Genre;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieLength;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class MovieForm {

    private String name;

    @NonNull
    private MovieLength movieLength;

    private Instant publishDate;

    private Genre genre;

    private String description;

    @NonNull
    private Money ticketPrice;

    @Valid
    @NotEmpty
    private Set<ScheduledMovie> scheduledMovies = new HashSet<>();

    @JsonCreator
    public MovieForm(@JsonProperty("name") String name,
                 @JsonProperty("movieLength") MovieLength movieLength,
                 @JsonProperty("genre") Genre genre,
                 @JsonProperty("publishDate") Instant publishDate,
                 @JsonProperty("description") String description,
                 @JsonProperty("ticketPrice") Money ticketPrice,
                 @JsonProperty("scheduledMovies") Set<ScheduledMovie> scheduledMovies) {
        this.name = name;
        this.movieLength = movieLength;
        this.genre=genre;
        this.publishDate=publishDate;
        this.description= description;
        this.ticketPrice=ticketPrice;
        this.scheduledMovies=scheduledMovies;
    }

}
