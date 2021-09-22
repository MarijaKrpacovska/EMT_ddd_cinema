package com.example.movie.services.impl;

import com.example.movie.domain.exceptions.MovieIdDoesNotExistException;
import com.example.movie.domain.exceptions.ScheduledMovieIdDoesNotExistException;
import com.example.movie.domain.models.Movie;
import com.example.movie.domain.models.MovieId;
import com.example.movie.domain.models.ScheduledMovie;
import com.example.movie.domain.models.ScheduledMovieId;
import com.example.movie.domain.repositories.MovieRepository;
import com.example.movie.services.MovieService;
import com.example.movie.services.forms.MovieForm;
import com.example.movie.services.forms.ScheduledMovieForm;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public void addScheduledMovie(MovieId movieId, ScheduledMovieForm scheduledMovieForm) throws MovieIdDoesNotExistException {
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
        movie.addScheduledMovie(scheduledMovieForm.getStartTime(), scheduledMovieForm.getEndTime());
        movieRepository.saveAndFlush(movie);
    }

    @Override
    public void deleteItem(MovieId movieId, ScheduledMovieId scheduledMovieId) throws MovieIdDoesNotExistException, ScheduledMovieIdDoesNotExistException {
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
        movie.deleteScheduledMovie(scheduledMovieId);
        movieRepository.saveAndFlush(movie);
    }

    @Override
    public Movie ticketAdded(MovieId movieId,MovieTime movieTime) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
        movie.increaseSales(movieTime);
        return movie;
    }

    @Override
    public Movie ticketRemoved(MovieId movieId,MovieTime movieTime ) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(MovieIdDoesNotExistException::new);
        movie.decreaseSales(movieTime);
        return movie;
    }


    private Movie toDomainObject(MovieForm movieForm) {
        var movie = new Movie(movieForm.getName(),movieForm.getMovieLength(),movieForm.getGenre(),movieForm.getPublishDate(), movieForm.getDescription(),movieForm.getTicketPrice());
        Set<ScheduledMovieForm> scheduledMoviesList = movieForm.getScheduledMovies();

        movieForm.getScheduledMovies().forEach(item->movie.addScheduledMovie(item.getStartTime(),item.getEndTime()));
        return movie;
    }



}
