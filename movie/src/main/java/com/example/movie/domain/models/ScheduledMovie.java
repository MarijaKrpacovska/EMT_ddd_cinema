package com.example.movie.domain.models;

import com.example.movie.domain.valueobjects.Money;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="scheduled_movie")
@Getter
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

    public void addSales() {
        this.sales = this.sales + 1;
    }

    public void removeSales() {
        this.sales -= 1;
    }


    public MovieTime scheduledFor(){
        return startTime;
    }


}
