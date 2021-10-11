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

@Getter
public class ScheduledMovie implements ValueObject {

    private ScheduledMovieId id;
    private Money ticketsPrice;
    private int sales;
    private MovieTime startTime;
    private MovieTime endTime;
    private MovieId movieId;

    private ScheduledMovie() {
        this.id= ScheduledMovieId.randomId(ScheduledMovieId.class);
        this.sales= 0;
        this.ticketsPrice = Money.valueOf(Currency.MKD,0);
        this.startTime= MovieTime.valueOf(0,0);
        this.endTime= MovieTime.valueOf(0,0);
        this.movieId=MovieId.randomId(MovieId.class);
    }

    @JsonCreator
    public ScheduledMovie(@JsonProperty("id") ScheduledMovieId id,
                          @JsonProperty("ticketsPrice") Money ticketsPrice,
                          @JsonProperty("sales") int sales,
                          @JsonProperty("startTime") MovieTime startTime,
                          @JsonProperty("endTime") MovieTime endTime,
                          @JsonProperty("movieId") MovieId movieId) {
        this.id = id;
        this.ticketsPrice = ticketsPrice;
        this.sales=sales;
        this.endTime=endTime;
        this.startTime=startTime;
        this.movieId=movieId;
    }
}

