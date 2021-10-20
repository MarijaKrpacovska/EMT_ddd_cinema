package com.example.movie.services.impl;

import com.example.movie.domain.exceptions.MovieIdDoesNotExistException;
import com.example.movie.domain.models.Movie;
import com.example.movie.domain.models.MovieId;
import com.example.movie.domain.repositories.MovieRepository;
import com.example.movie.domain.valueobjects.Rating;
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

//    @Override
//    public Optional<Movie> editMovie(MovieId id, MovieForm movieForm) {
//        Movie movie = this.movieRepository.findById(id).orElseThrow(MovieIdDoesNotExistException::new);
//        Movie.build(movieForm.getName(),movieForm.getMovieLength(),movieForm.getGenre(),movieForm.getPublishDate(),
//                movieForm.getDescription(),movieForm.getTicketPrice(),movieForm.getUrl());
//        return Optional.empty();
//    }

//    @Override
//    public Optional<Movie> addScheduledMovie(MovieId movieId, ScheduledMovieForm scheduledMovieForm) throws MovieIdDoesNotExistException {
//        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
//        movie.addScheduledMovie(scheduledMovieForm.getStartTime(), scheduledMovieForm.getEndTime());
//        var newMovie =movieRepository.saveAndFlush(movie);
//        return Optional.of(newMovie);
//    }
//
//    @Override
//    public void deleteItem(MovieId movieId, ScheduledMovieId scheduledMovieId) throws MovieIdDoesNotExistException, ScheduledMovieIdDoesNotExistException {
//        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
//        movie.deleteScheduledMovie(scheduledMovieId);
//        movieRepository.saveAndFlush(movie);
//    }
//
//    @Override
//    public Movie ticketAdded(MovieId movieId,MovieTime movieTime) {
//        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
//        movie.increaseSales(movieTime);
//        return movie;
//    }
//
//    @Override
//    public Movie ticketRemoved(MovieId movieId,MovieTime movieTime ) {
//        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
//        movie.decreaseSales(movieTime);
//        return movie;
//    }

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
        var movie = new Movie(movieForm.getName(),movieForm.getMovieLength(),movieForm.getGenre(),LocalDate.parse(movieForm.getPublishDate(),formatter), movieForm.getDescription() ,movieForm.getUrl(), movieForm.getTrailerUrl(),0, new Rating(0.0,0));
      //  Set<ScheduledMovieForm> scheduledMoviesList = movieForm.getScheduledMovies();

      //  movieForm.getScheduledMovies().forEach(item->movie.addScheduledMovie(item.getStartTime(),item.getEndTime()));
        return movie;
    }



}
