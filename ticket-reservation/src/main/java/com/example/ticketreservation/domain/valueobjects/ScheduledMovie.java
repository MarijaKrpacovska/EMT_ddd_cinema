package com.example.ticketreservation.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.money.Currency;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import java.time.LocalDate;

@Getter
public class ScheduledMovie implements ValueObject {

    private ScheduledMovieId id;
    private Money ticketPrice;
    private int sales;
    private MovieTime startTime;
    private MovieTime endTime;
    private MovieId movieId;

    //todo: check if valueObjects have constructors
    private ScheduledMovie() {
        this.id= ScheduledMovieId.randomId(ScheduledMovieId.class);
        this.sales= 0;
        this.ticketPrice = Money.valueOf(Currency.MKD,0);
        this.startTime= MovieTime.valueOf(0,0,LocalDate.of(2021,1,1));
        this.endTime= MovieTime.valueOf(0,0,LocalDate.of(2021,1,1));
        this.movieId=MovieId.randomId(MovieId.class);
    }

    @JsonCreator
    public ScheduledMovie(@JsonProperty("id") ScheduledMovieId id,
                          @JsonProperty("ticketPrice") Money ticketPrice,
                          @JsonProperty("sales") int sales,
                          @JsonProperty("startTime") MovieTime startTime,
                          @JsonProperty("endTime") MovieTime endTime,
                          @JsonProperty("movieId") MovieId movieId) {
        this.id = id;
        this.ticketPrice = ticketPrice;
        this.sales=sales;
        this.endTime=endTime;
        this.startTime=startTime;
        this.movieId=movieId;
    }
}

