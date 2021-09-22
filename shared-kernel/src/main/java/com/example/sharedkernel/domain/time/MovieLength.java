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

    public double toMin(){
        if(unitOfTime.equals(UnitOfTime.min))
            return length;
        else if(unitOfTime.equals(UnitOfTime.hour))
            return length/60;
        else
            return length*60;
    }

    public double toHour(){
        if(unitOfTime.equals(UnitOfTime.min))
            return length*60;
        else if(unitOfTime.equals(UnitOfTime.hour))
            return length;
        else
            return length*3600;
    }

    public double toSec(){
        if(unitOfTime.equals(UnitOfTime.min))
            return length/60;
        else if(unitOfTime.equals(UnitOfTime.hour))
            return length/3600;
        else
            return length;
    }

    public MovieLength(double length, UnitOfTime unitOfTime) {
        this.length = length;
        this.unitOfTime = unitOfTime;
    }
}
