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

    public MovieLength(double length, UnitOfTime unitOfTime) {
        this.length = length;
        this.unitOfTime = unitOfTime;
    }
}
