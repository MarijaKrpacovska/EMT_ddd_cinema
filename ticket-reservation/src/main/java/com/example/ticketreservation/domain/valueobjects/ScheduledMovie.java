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
    private int capacity;
    private Money ticketPrice;
    private int sales;
    private MovieTime startTime;
    private MovieTime endTime;

    private ScheduledMovie() {
        this.id= ScheduledMovieId.randomId(ScheduledMovieId.class);
        this.capacity= 0;
        this.ticketPrice = Money.valueOf(Currency.MKD,0);
    }

    @JsonCreator
    public ScheduledMovie(@JsonProperty("id") ScheduledMovieId id,
                          @JsonProperty("capacity") int capacity,
                          @JsonProperty("ticketPrice") Money ticketPrice,
                          @JsonProperty("sales") int sales,
                          @JsonProperty("startTime") MovieTime startTime,
                          @JsonProperty("endTime") MovieTime endTime) {
        this.id = id;
        this.sales = sales;
        this.ticketPrice = ticketPrice;
        this.capacity=capacity;
        this.endTime=endTime;
        this.startTime=startTime;
    }
}

