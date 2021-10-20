package com.example.movie.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.money.Currency;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Rating implements ValueObject {

    private final double rating;

    private final int numberOfRatings;

    public Rating() {
        rating=0.0;
        numberOfRatings=0;
    }

    public Rating(double rating, int numberOfRatings) {
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
    }

    public Rating calculate(double newRating){
        int finalNumberOfRatings = numberOfRatings + 1;
        double finalRating = ((rating * numberOfRatings) + newRating) / finalNumberOfRatings;
        return new Rating(finalRating,finalNumberOfRatings);
    }

    public Rating compare(Rating r){
        if(r.rating > rating)
            return r;
        else return new Rating(rating,numberOfRatings);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating ratingObj = (Rating) o;
        return rating == ratingObj.rating && numberOfRatings == ratingObj.numberOfRatings;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(currency, amount);
//    }


}