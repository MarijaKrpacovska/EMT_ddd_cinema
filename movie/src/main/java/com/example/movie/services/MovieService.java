package com.example.movie.services;

import com.example.movie.domain.exceptions.MovieIdDoesNotExistException;
import com.example.movie.domain.exceptions.ScheduledMovieIdDoesNotExistException;
import com.example.movie.domain.models.Movie;
import com.example.movie.domain.models.MovieId;
import com.example.movie.domain.models.ScheduledMovieId;
import com.example.movie.services.forms.MovieForm;
import com.example.movie.services.forms.ScheduledMovieForm;
import com.example.sharedkernel.domain.time.MovieTime;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    public MovieId addMovie(MovieForm movieForm);

    List<Movie> findAll();

    Optional<Movie> findById(MovieId id);

    void addScheduledMovie(MovieId movieId, ScheduledMovieForm scheduledMovieForm) throws MovieIdDoesNotExistException;

    void deleteItem(MovieId movieId, ScheduledMovieId scheduledMovieId) throws MovieIdDoesNotExistException, ScheduledMovieIdDoesNotExistException;

//    public Movie ticketAdded(MovieId movieId, MovieTime movieTime);
//
//    public Movie ticketRemoved(MovieId movieId, MovieTime movieTime);
}
