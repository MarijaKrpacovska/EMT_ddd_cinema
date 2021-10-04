package com.example.scheduledmovie.domain.repository;

import com.example.scheduledmovie.domain.models.ScheduledMovie;
import com.example.scheduledmovie.domain.models.ScheduledMovieId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledMovieRepository extends JpaRepository<ScheduledMovie, ScheduledMovieId> {
}
