package com.example.scheduledmovie.services.impl;


import com.example.scheduledmovie.domain.exceptions.ScheduledMovieIdDoesNotExistException;
import com.example.scheduledmovie.domain.models.ScheduledMovie;
import com.example.scheduledmovie.domain.models.ScheduledMovieId;
import com.example.scheduledmovie.domain.models.ScheduledMovieStatus;
import com.example.scheduledmovie.domain.repository.ScheduledMovieRepository;
import com.example.scheduledmovie.domain.valueobjects.MovieId;
import com.example.scheduledmovie.services.ScheduledMovieService;
import com.example.scheduledmovie.services.forms.ScheduledMovieForm;
import com.example.sharedkernel.domain.events.schedulingMovie.MovieScheduled;
import com.example.sharedkernel.domain.events.schedulingMovie.ScheduledMovieCanceled;
import com.example.sharedkernel.domain.events.ticketReservations.ReservationCanceled;
import com.example.sharedkernel.domain.time.MovieTime;
import com.example.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ScheduledMovieServiceImpl implements ScheduledMovieService {

    private final Validator validator;
    private final DomainEventPublisher domainEventPublisher;
    private final ScheduledMovieRepository scheduledMovieRepository;

    @Override
    public Optional<ScheduledMovie> save(ScheduledMovieForm movieForm) {
        Objects.requireNonNull(movieForm,"scheduled movie form must not be null.");
        var constraintViolations = validator.validate(movieForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The movie form is not valid", constraintViolations);
        }
        var newMovie = scheduledMovieRepository.saveAndFlush(toDomainObject(movieForm));
        //pri kreiranjeto na nov ScheduledMovie da se zgolemi i brojot na scheduledMovies:
        domainEventPublisher.publish(new MovieScheduled(movieForm.getMovieId()));
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
    public Optional<ScheduledMovie> rescheduleMovie(ScheduledMovieId id, String time, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ScheduledMovie scheduledMovie = scheduledMovieRepository.findById(id).orElseThrow(ScheduledMovieIdDoesNotExistException::new);
        scheduledMovie.rescheduleMovie(MovieTime.valueOf(Integer.parseInt(time.split(":")[0]),Integer.parseInt(time.split(":")[1]),LocalDate.parse(date,formatter)));
        return Optional.of(scheduledMovie);
    }

    @Override
    public List<ScheduledMovie> findAllByMovieId(MovieId id) {
        return scheduledMovieRepository.findAllByMovieId(id);
    }

    @Override
    public ScheduledMovie reservationConfirmed(ScheduledMovieId scheduledMovieId, int quantity) {
        ScheduledMovie scheduledMovie = scheduledMovieRepository
                .findById(scheduledMovieId).orElseThrow(ScheduledMovieIdDoesNotExistException::new);
        scheduledMovie.addSales(quantity);
        return scheduledMovie;
    }

    @Override
    public ScheduledMovie reservationCanceled(ScheduledMovieId scheduledMovieId, int quantity) {
        ScheduledMovie scheduledMovie = scheduledMovieRepository.findById(scheduledMovieId).orElseThrow(ScheduledMovieIdDoesNotExistException::new);
        scheduledMovie.removeSales(quantity);
        return scheduledMovie;
    }

    @Override
    public void cancelScheduledMovie(ScheduledMovieId scheduledMovieId){
        ScheduledMovie scheduledMovie = scheduledMovieRepository
                .findById(scheduledMovieId).orElseThrow(ScheduledMovieIdDoesNotExistException::new);
        scheduledMovie.cancelScheduledMovie();
        domainEventPublisher.publish(new ScheduledMovieCanceled(scheduledMovie.getMovieId().getId()));
        scheduledMovieRepository.saveAndFlush(scheduledMovie);
    }

    private ScheduledMovie toDomainObject(ScheduledMovieForm scheduledMovieForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var mov = MovieTime.valueOf(Integer.parseInt(scheduledMovieForm.getStartTime().split(":")[0]),
                Integer.parseInt(scheduledMovieForm.getStartTime().split(":")[1]),
                            LocalDate.parse(scheduledMovieForm.getStartDate(),formatter));
        var movie = new ScheduledMovie(scheduledMovieForm.getSales(),mov, scheduledMovieForm.getTicketPrice(), new MovieId(scheduledMovieForm.getMovieId()), ScheduledMovieStatus.ACTIVE);
        return movie;
    }

}
