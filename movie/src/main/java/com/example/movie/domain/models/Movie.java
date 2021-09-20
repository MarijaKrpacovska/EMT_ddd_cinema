package com.example.movie.domain.models;

import com.example.movie.domain.valueobjects.MovieLength;
import com.example.movie.domain.valueobjects.UnitOfTime;
import com.example.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="movie")
public class Movie extends AbstractEntity<MovieId> {

    private String name;

    private MovieLength movieLength;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private LocalDateTime publishDate;

    private String description;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ScheduledMovie> scheduledMovies;

    public Movie() {

    }
}
