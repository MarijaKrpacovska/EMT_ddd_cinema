package com.example.sharedkernel.domain.time;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class MovieLength implements ValueObject {
    private final double length;

    @Enumerated(value = EnumType.STRING)
    private final UnitOfTime unitOfTime;

    protected MovieLength(){
        unitOfTime=UnitOfTime.min;
        length=0;
    }

    public MovieLength toMin(){
        if(unitOfTime.equals(UnitOfTime.min))
            return new MovieLength(length,unitOfTime);
        else if(unitOfTime.equals(UnitOfTime.hour))
            return new MovieLength(length/60,UnitOfTime.min);
        else
            return new MovieLength(length*60,UnitOfTime.min);
    }

    public MovieLength toHour(){
        if(unitOfTime.equals(UnitOfTime.min))
            return new MovieLength(length*60,UnitOfTime.hour);
        else if(unitOfTime.equals(UnitOfTime.hour))
            return new MovieLength(length,unitOfTime);
        else
            return new MovieLength(length*3600,UnitOfTime.hour);
    }

    public MovieLength toSec(){
        if(unitOfTime.equals(UnitOfTime.min))
            return new  MovieLength(length/60,UnitOfTime.sec);
        else if(unitOfTime.equals(UnitOfTime.hour))
            return new MovieLength(length/3600,UnitOfTime.sec);
        else
            return new MovieLength(length,unitOfTime);
    }

    public MovieLength(double length, UnitOfTime unitOfTime) {
        this.length = length;
        this.unitOfTime = unitOfTime;
    }
}
