package com.example.movie.domain.repositories;

import com.example.movie.domain.models.Movie;
import com.example.movie.domain.models.MovieId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository  extends JpaRepository<Movie, MovieId> {
}
