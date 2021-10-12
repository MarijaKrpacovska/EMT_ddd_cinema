package com.example.scheduledmovie.domain.repository;

import com.example.scheduledmovie.domain.models.ScheduledMovie;
import com.example.scheduledmovie.domain.models.ScheduledMovieId;
import com.example.scheduledmovie.domain.valueobjects.MovieId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduledMovieRepository extends JpaRepository<ScheduledMovie, ScheduledMovieId> {

    List<ScheduledMovie> findAllByMovieId(MovieId movieId);
}
