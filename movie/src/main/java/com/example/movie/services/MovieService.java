package com.example.movie.services;

import com.example.movie.domain.exceptions.MovieIdDoesNotExistException;
import com.example.movie.domain.models.Movie;
import com.example.movie.domain.models.MovieId;
import com.example.movie.services.forms.MovieForm;
import com.example.sharedkernel.domain.time.MovieTime;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    public Optional<Movie> addMovie(MovieForm movieForm);

    List<Movie> findAll();

    Optional<Movie> findById(MovieId id);

    Movie scheduledMovieAdded(MovieId movieId);

    Movie scheduledMovieRemoved(MovieId movieId);

//    Optional<Movie> editMovie(MovieId id, MovieForm movieForm);

//    Optional<Movie> addScheduledMovie(MovieId movieId, ScheduledMovieForm scheduledMovieForm) throws MovieIdDoesNotExistException;
//
//    void deleteItem(MovieId movieId, ScheduledMovieId scheduledMovieId) throws MovieIdDoesNotExistException, ScheduledMovieIdDoesNotExistException;
//
//    public Movie ticketAdded(MovieId movieId, MovieTime movieTime);
//
//    public Movie ticketRemoved(MovieId movieId, MovieTime movieTime);

    
}
