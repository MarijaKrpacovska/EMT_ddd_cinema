package com.example.sharedkernel.domain.time;

import com.example.sharedkernel.domain.base.ValueObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@Getter
public class MovieTime implements ValueObject {

    private final int hour;
    private final int minutes;
    private final LocalDate date;

    protected MovieTime(){
        date = LocalDate.of(2021,11,11);
        hour=0;
        minutes=0;
    }

    public MovieTime(int hour, int minutes, LocalDate date) {
        this.hour = hour;
        this.minutes = minutes;
        this.date=date;
    }

    public static MovieTime valueOf(int hour, int minutes, LocalDate date) {
        return new MovieTime(hour,minutes,date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieTime movieTime = (MovieTime) o;
        return hour == movieTime.hour && minutes == movieTime.minutes && date.equals(movieTime.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minutes);
    }
}
