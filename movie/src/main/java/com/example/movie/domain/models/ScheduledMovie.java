package com.example.movie.domain.models;

import com.example.movie.domain.valueobjects.Money;
import com.example.movie.domain.valueobjects.MovieTime;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="scheduled_movie")
public class ScheduledMovie extends AbstractEntity<ScheduledMovieId> {

    private int capacity;

    private Money ticketPrice;

    private int sales;

    @AttributeOverrides({
            @AttributeOverride(name="hour", column = @Column(name="starting_hour")),
            @AttributeOverride(name="minutes", column = @Column(name="starting_minutes"))
    })
    private MovieTime startTime;

    @AttributeOverrides({
            @AttributeOverride(name="hour", column = @Column(name="ending_hour")),
            @AttributeOverride(name="minutes", column = @Column(name="ending_minutes"))
    })
    private MovieTime endTime;

    private ScheduledMovie() {
        super(DomainObjectId.randomId(ScheduledMovieId.class));
    }

    public ScheduledMovie(@NonNull Money ticketPrice, int capacity, int sales,@NonNull MovieTime startTime, @NonNull MovieTime endTime) {
        super(DomainObjectId.randomId(ScheduledMovieId.class));
        this.ticketPrice = ticketPrice;
        this.capacity = capacity;
        this.sales = sales;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public void addSales(int qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }


}
