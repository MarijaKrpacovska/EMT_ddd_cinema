package com.example.scheduledmovie.services;

import com.example.scheduledmovie.domain.models.ScheduledMovie;
import com.example.scheduledmovie.domain.models.ScheduledMovieId;
import com.example.scheduledmovie.services.forms.ScheduledMovieForm;
import com.example.sharedkernel.domain.time.MovieTime;

import java.util.List;
import java.util.Optional;

public interface ScheduledMovieService {

    Optional<ScheduledMovie> save(ScheduledMovieForm scheduledMovieForm);

    List<ScheduledMovie> findAll();

    Optional<ScheduledMovie> findById(ScheduledMovieId id);

//    Optional<Movie> addScheduledMovie(MovieId movieId, ScheduledMovieForm scheduledMovieForm) throws MovieIdDoesNotExistException;
//
//    void deleteItem(MovieId movieId, ScheduledMovieId scheduledMovieId) throws MovieIdDoesNotExistException, ScheduledMovieIdDoesNotExistException;
//
    ScheduledMovie reservationConfirmed(ScheduledMovieId scheduledMovieId, int quantity);

    ScheduledMovie reservationCanceled(ScheduledMovieId scheduledMovieId, int quantity);
}
