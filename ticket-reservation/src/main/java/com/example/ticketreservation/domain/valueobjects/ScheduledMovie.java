package com.example.ticketreservation.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class ScheduledMovie implements ValueObject {

    private ScheduledMovieId id;
    private int capacity;
    private Money ticketPrice;

    private ScheduledMovie() {
        this.id= ScheduledMovieId.randomId(ScheduledMovieId.class);
        this.capacity= 0;
        this.ticketPrice = Money.valueOf(Currency.MKD,0);
    }

    @JsonCreator
    public ScheduledMovie(ScheduledMovieId id, int capacity, Money ticketPrice) {
        this.id = id;
        this.capacity = capacity;
        this.ticketPrice = ticketPrice;
    }
}

