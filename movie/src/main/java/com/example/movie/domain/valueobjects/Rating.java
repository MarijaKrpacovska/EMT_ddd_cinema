package com.example.movie.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.money.Currency;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Rating implements ValueObject {

    private final double ratingNumber;

    private final int number;

    public Rating() {
        ratingNumber=0.0;
        number=0;
    }

    public Rating(double ratingNumber, int number) {
        this.ratingNumber = ratingNumber;
        this.number = number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
            return ratingNumber == rating.ratingNumber && number == rating.number;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(currency, amount);
//    }


}