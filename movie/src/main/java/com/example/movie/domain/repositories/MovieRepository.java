package com.example.movie.domain.repositories;

import com.example.movie.domain.models.Movie;
import com.example.movie.domain.models.MovieId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MovieRepository  extends JpaRepository<Movie, MovieId> {
    Page<Movie> findAll(Pageable pageable);
}
