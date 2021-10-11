package com.example.ticketreservation.service.impl;

import com.example.sharedkernel.domain.time.MovieTime;
import com.example.sharedkernel.infra.DomainEventPublisher;
import com.example.ticketreservation.domain.exceptions.TicketIdDoesNotExist;
import com.example.ticketreservation.domain.exceptions.TicketReservationIdDoesNotExist;
import com.example.ticketreservation.domain.models.ReservationStatus;
import com.example.ticketreservation.domain.models.TicketId;
import com.example.ticketreservation.domain.models.TicketReservation;
import com.example.ticketreservation.domain.models.TicketReservationId;
import com.example.ticketreservation.domain.repository.TicketReservationRepository;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovie;
import com.example.ticketreservation.service.TicketReservationService;
import com.example.ticketreservation.service.forms.TicketForm;
import com.example.ticketreservation.service.forms.TicketReservationForm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TicketReservationServiceImpl implements TicketReservationService {

    private final TicketReservationRepository ticketReservationRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;

    @Override
    public Optional<TicketReservation> makeReservation(TicketReservationForm ticketReservationForm) {
        Objects.requireNonNull(ticketReservationForm,"ticket reservation must not be null.");
        var constraintViolations = validator.validate(ticketReservationForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order reservation form is not valid", constraintViolations);
        }
        var newTicketReservation = ticketReservationRepository.saveAndFlush(toDomainObject(ticketReservationForm));
      //  newTicketReservation.getTickets().forEach(item -> domainEventPublisher.publish(new TicketAdded(item.getScheduledMovieId().getId(),new MovieTime(10,10))));
        return Optional.of(newTicketReservation);
    }

    @Override
    public Optional<TicketReservation> createReservation(TicketReservationForm ticketReservationForm, ScheduledMovie scheduledMovie) {
        Objects.requireNonNull(ticketReservationForm,"ticket reservation must not be null.");
        var constraintViolations = validator.validate(ticketReservationForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order reservation form is not valid", constraintViolations);
        }
        var newTicketReservation = ticketReservationRepository.saveAndFlush(toDomainObject(ticketReservationForm));
        //  newTicketReservation.getTickets().forEach(item -> domainEventPublisher.publish(new TicketAdded(item.getScheduledMovieId().getId(),new MovieTime(10,10))));
        return Optional.of(newTicketReservation);
    }


    @Override
    public List<TicketReservation> findAll() {
        return ticketReservationRepository.findAll();
    }

    @Override
    public Optional<TicketReservation> findById(TicketReservationId id) {
        return ticketReservationRepository.findById(id);
    }

    @Override
    public Optional<TicketReservation> findByReservationStatus(ReservationStatus reservationStatus) {
        return ticketReservationRepository.findByReservationStatus(reservationStatus);
    }

    @Override
    public void addTicket(TicketReservationId ticketReservationId, TicketForm ticketForm) throws TicketReservationIdDoesNotExist {
        TicketReservation ticketReservation = ticketReservationRepository.findById(ticketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
        ticketReservation.addTicket(ticketForm.getScheduledMovie(),ticketForm.getQty());
        ticketReservationRepository.saveAndFlush(ticketReservation);
//        domainEventPublisher.publish(new TicketAdded(ticketForm.getScheduledMovie().getId(),new MovieTime(4,30)));

    }

    @Override
    public void deleteTicket(TicketReservationId ticketReservationId, TicketId ticketId) throws TicketReservationIdDoesNotExist, TicketIdDoesNotExist {
        TicketReservation ticketReservation = ticketReservationRepository.findById(ticketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
        ticketReservation.removeTicket(ticketId);
        ticketReservationRepository.saveAndFlush(ticketReservation);
    //    domainEventPublisher.publish(new TicketRemoved(ticketId.getId(),new MovieTime(4,30)));
    }

    @Override
    public void cancelReservation(TicketReservationId ticketReservationId) throws TicketReservationIdDoesNotExist {
        TicketReservation ticketReservation = ticketReservationRepository.findById(ticketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
        ticketReservation.cancel();
        ticketReservationRepository.saveAndFlush(ticketReservation);
    }

    @Override
    public void confirmReservation(TicketReservationId ticketReservationId) throws TicketReservationIdDoesNotExist {
        TicketReservation ticketReservation = ticketReservationRepository.findById(ticketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
        ticketReservation.confirm();
        ticketReservationRepository.saveAndFlush(ticketReservation);
    }

    private TicketReservation toDomainObject(TicketReservationForm ticketReservationForm) {
        var ticketReservation = new TicketReservation(Instant.now(),ticketReservationForm.getCurrency(),ticketReservationForm.getReservationStatus(),ticketReservationForm.getPaymentMethod());
        ticketReservationForm.getTickets().forEach(item->ticketReservation.addTicket(item.getScheduledMovie(),item.getQty() ));
        return ticketReservation;
    }

}
