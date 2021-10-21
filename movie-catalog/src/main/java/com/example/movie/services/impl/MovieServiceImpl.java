package com.example.movie.services.impl;

import com.example.movie.domain.exceptions.MovieIdDoesNotExistException;
import com.example.movie.domain.models.Movie;
import com.example.movie.domain.models.MovieId;
import com.example.movie.domain.repositories.MovieRepository;
import com.example.movie.domain.valueobjects.Rating;
import com.example.movie.domain.valueobjects.Video;
import com.example.movie.services.MovieService;
import com.example.movie.services.forms.MovieForm;
import com.example.sharedkernel.domain.events.schedulingMovie.MovieScheduled;
import com.example.sharedkernel.domain.genre.Genre;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
@Transactional
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final Validator validator;
    private final MovieRepository movieRepository;

    @Override
    public Optional<Movie> addMovie(MovieForm movieForm) {
        Objects.requireNonNull(movieForm,"movie must not be null.");
        var constraintViolations = validator.validate(movieForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The movie form is not valid", constraintViolations);
        }
        var newMovie = movieRepository.saveAndFlush(toDomainObject(movieForm));
        return Optional.of(newMovie);

    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(MovieId id) {
        return movieRepository.findById(id);
    }


    public Movie scheduledMovieAdded(MovieId movieId){
        Movie movie = this.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
        movie.addTimesScheduled();
        return movie;
    }

    @Override
    public Movie scheduledMovieRemoved(MovieId movieId){
        Movie movie = this.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
        movie.decreaseTimesScheduled();
        return movie;
    }

    @Override
    public Optional<Movie> addRating(double rating, MovieId movieId) {
        Movie movie = this.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
        movie.rate(rating);
        return Optional.of(movie);
    }

    @Override
    public Page<Movie> findAllWithPagination(Pageable pageable) {
        return this.movieRepository.findAll(pageable);
    }

    @Override
    public List<Movie> findAllByGenre(Genre genre) {
        return this.movieRepository.findAllByGenre(genre);
    }


    private Movie toDomainObject(MovieForm movieForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var movie = new Movie(movieForm.getName(),movieForm.getMovieLength(),movieForm.getGenre(),LocalDate.parse(movieForm.getPublishDate(),formatter), movieForm.getDescription() ,movieForm.getMoviePoster(),movieForm.getMovieAdvertisementImage(), Video.buildVideoWithEmbeddableUrl(movieForm.getTrailerUrl()),0, new Rating(0.0,0));
        return movie;
    }



}
