package com.example.scheduledmovie.services.forms;//package com.example.movie.services.forms;

import com.example.scheduledmovie.domain.valueobjects.Movie;
import com.example.scheduledmovie.domain.valueobjects.MovieId;
import com.example.sharedkernel.domain.money.Currency;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class ScheduledMovieForm {

    private int sales;

    @NotNull
    private MovieTime startTime;

    @NotNull
    private MovieTime endTime;

    @NotNull
    private Money ticketPrice;

    @NotNull
    private String movieId;

    @JsonCreator
    public ScheduledMovieForm(@JsonProperty("sales") int sales,
                                 @JsonProperty("startTime") MovieTime startTime,
                                 @JsonProperty("endTime") MovieTime endTime,
                                 @JsonProperty("ticketPrice") Money ticketPrice,
                                 @JsonProperty("movieId") String movieId
    ){
        this.sales=sales;
        this.startTime=startTime;
        this.endTime=endTime;
        this.ticketPrice=ticketPrice;
        this.movieId=movieId;
    }

}
