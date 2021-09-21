package com.example.sharedkernel.domain.time;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieTime movieTime = (MovieTime) o;
        return hour == movieTime.hour && minutes == movieTime.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minutes);
    }
}
