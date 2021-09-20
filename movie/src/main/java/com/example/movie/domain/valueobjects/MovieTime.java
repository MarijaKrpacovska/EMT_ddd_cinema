package com.example.movie.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class MovieTime implements ValueObject {
    private final int hour;
    private final int minutes;

    protected MovieTime(){
        hour=0;
        minutes=0;
    }

    public MovieTime(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }
}
