package com.example.scheduledmovie.services.impl;


import com.example.scheduledmovie.domain.exceptions.ScheduledMovieIdDoesNotExistException;
import com.example.scheduledmovie.domain.models.ScheduledMovie;
import com.example.scheduledmovie.domain.models.ScheduledMovieId;
import com.example.scheduledmovie.domain.repository.ScheduledMovieRepository;
import com.example.scheduledmovie.domain.valueobjects.MovieId;
import com.example.scheduledmovie.services.ScheduledMovieService;
import com.example.scheduledmovie.services.forms.ScheduledMovieForm;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ScheduledMovieServiceImpl implements ScheduledMovieService {

    private final Validator validator;
    private final ScheduledMovieRepository scheduledMovieRepository;

    @Override
    public Optional<ScheduledMovie> save(ScheduledMovieForm movieForm) {
        Objects.requireNonNull(movieForm,"scheduled movie form must not be null.");
        var constraintViolations = validator.validate(movieForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The movie form is not valid", constraintViolations);
        }
        var newMovie = scheduledMovieRepository.saveAndFlush(toDomainObject(movieForm));
        return Optional.of(newMovie);
    }

    @Override
    public List<ScheduledMovie> findAll() {
        return scheduledMovieRepository.findAll();
    }

    @Override
    public Optional<ScheduledMovie> findById(ScheduledMovieId id) {
        return scheduledMovieRepository.findById(id);
    }

    @Override
    public ScheduledMovie reservationConfirmed(ScheduledMovieId scheduledMovieId, int quantity) {
        ScheduledMovie scheduledMovie = scheduledMovieRepository.findById(scheduledMovieId).orElseThrow(ScheduledMovieIdDoesNotExistException::new);
        scheduledMovie.addSales(quantity);
        return scheduledMovie;
    }

    @Override
    public ScheduledMovie reservationCanceled(ScheduledMovieId scheduledMovieId, int quantity) {
        ScheduledMovie scheduledMovie = scheduledMovieRepository.findById(scheduledMovieId).orElseThrow(ScheduledMovieIdDoesNotExistException::new);
        scheduledMovie.removeSales(quantity);
        return scheduledMovie;
    }

    private ScheduledMovie toDomainObject(ScheduledMovieForm scheduledMovieForm) {
        var movie = new ScheduledMovie(scheduledMovieForm.getSales(),scheduledMovieForm.getStartTime(), scheduledMovieForm.getEndTime(), scheduledMovieForm.getTicketPrice(), new MovieId(scheduledMovieForm.getMovieId()));
        return movie;
    }

}
