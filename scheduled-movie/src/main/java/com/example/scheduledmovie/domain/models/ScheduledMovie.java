package com.example.scheduledmovie.domain.models;//package com.example.movie.domain.models;

import com.example.scheduledmovie.domain.valueobjects.Movie;
import com.example.scheduledmovie.domain.valueobjects.MovieId;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="scheduled_movie")
@Getter
public class ScheduledMovie extends AbstractEntity<ScheduledMovieId> {

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

    private Money ticketsPrice;

    @AttributeOverride(name = "id", column = @Column(name = "movie_id", nullable = false))
    private MovieId movieId;

    @Enumerated(EnumType.STRING)
    private ScheduledMovieStatus scheduledMovieStatus;

    private ScheduledMovie() {
        super(DomainObjectId.randomId(ScheduledMovieId.class));
    }

    public ScheduledMovie(int sales,@NonNull MovieTime startTime, @NonNull MovieTime endTime, Money money, @NonNull MovieId movieId, ScheduledMovieStatus scheduledMovieStatus) {
        super(DomainObjectId.randomId(ScheduledMovieId.class));
        this.sales = sales;
        this.startTime=startTime;
        this.endTime=endTime;
        this.ticketsPrice = money;
        this.movieId=movieId;
        this.scheduledMovieStatus=scheduledMovieStatus;
    }

    //se koristi za zgolemuvanje na prodazhbite za ova prikazhuvanje na film.
    public void addSales(int qty) {
        this.sales = this.sales + qty;
    }

    //se koristi za namaluvanje na prodazhbite za ova prikazhuvanje na film.
    public void removeSales(int qty) {
        this.sales -= qty;
    }

    public void cancelScheduledMovie(){
        this.scheduledMovieStatus=ScheduledMovieStatus.CANCELED;
    }

    public MovieTime scheduledFor(){
        return startTime;
    }


}
