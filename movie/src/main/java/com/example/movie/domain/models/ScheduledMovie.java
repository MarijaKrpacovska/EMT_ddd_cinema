package com.example.movie.domain.models;

import com.example.movie.domain.valueobjects.MovieTime;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.*;

@Entity
@Table(name="scheduled_movie")
public class ScheduledMovie extends AbstractEntity<ScheduledMovieId> {
    private int capacity;

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

}
