package com.example.ticketreservation.service.impl;

import com.example.sharedkernel.domain.events.ticketReservations.TicketAdded;
import com.example.sharedkernel.domain.events.ticketReservations.TicketRemoved;
import com.example.sharedkernel.domain.time.MovieTime;
import com.example.sharedkernel.infra.DomainEventPublisher;
import com.example.ticketreservation.domain.exceptions.TicketIdDoesNotExist;
import com.example.ticketreservation.domain.exceptions.TicketReservationIdDoesNotExist;
import com.example.ticketreservation.domain.models.TicketId;
import com.example.ticketreservation.domain.models.TicketReservation;
import com.example.ticketreservation.domain.models.TicketReservationId;
import com.example.ticketreservation.domain.repository.TicketReservationRepository;
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
    public TicketReservationId makeReservation(TicketReservationForm ticketReservationForm) {
        Objects.requireNonNull(ticketReservationForm,"ticket reservation must not be null.");
        var constraintViolations = validator.validate(ticketReservationForm);
        if (constraintViolations.size()>0) {
            throw new ConstraintViolationException("The order reservation form is not valid", constraintViolations);
        }
        var newTicketReservation = ticketReservationRepository.saveAndFlush(toDomainObject(ticketReservationForm));
      //  newTicketReservation.getTickets().forEach(item -> domainEventPublisher.publish(new TicketAdded(item.getMovieId().getId(),new MovieTime(10,10))));
        return newTicketReservation.getId();

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
    public void addTicket(TicketReservationId ticketReservationId, TicketForm ticketForm) throws TicketReservationIdDoesNotExist {
        TicketReservation ticketReservation = ticketReservationRepository.findById(ticketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
        ticketReservation.addTicket(ticketForm.getMovie(),ticketForm.getQty());
        ticketReservationRepository.saveAndFlush(ticketReservation);
      //  domainEventPublisher.publish(new TicketAdded(ticketForm.getMovie().getMovieId().getId(),new MovieTime(4,30)));

    }

    @Override
    public void deleteTicket(TicketReservationId ticketReservationId, TicketId ticketId) throws TicketReservationIdDoesNotExist, TicketIdDoesNotExist {
        TicketReservation ticketReservation = ticketReservationRepository.findById(ticketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
        ticketReservation.removeTicket(ticketId);
        ticketReservationRepository.saveAndFlush(ticketReservation);
     //   domainEventPublisher.publish(new TicketRemoved(ticketId.getId(),new MovieTime(4,30)));
    }

    private TicketReservation toDomainObject(TicketReservationForm ticketReservationForm) {
        var ticketReservation = new TicketReservation(Instant.now(),ticketReservationForm.getCurrency());
        ticketReservationForm.getTickets().forEach(item->ticketReservation.addTicket(item.getMovie(),item.getQty() ));
        return ticketReservation;
    }

}
