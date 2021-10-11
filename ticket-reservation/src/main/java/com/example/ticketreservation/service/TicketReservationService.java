package com.example.ticketreservation.service;

import com.example.ticketreservation.domain.exceptions.TicketIdDoesNotExist;
import com.example.ticketreservation.domain.exceptions.TicketReservationIdDoesNotExist;
import com.example.ticketreservation.domain.models.ReservationStatus;
import com.example.ticketreservation.domain.models.TicketId;
import com.example.ticketreservation.domain.models.TicketReservation;
import com.example.ticketreservation.domain.models.TicketReservationId;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovie;
import com.example.ticketreservation.service.forms.TicketForm;
import com.example.ticketreservation.service.forms.TicketReservationForm;

import java.util.List;
import java.util.Optional;

public interface TicketReservationService {
    Optional<TicketReservation>  makeReservation(TicketReservationForm ticketReservationForm);

    Optional<TicketReservation>  createReservation(TicketReservationForm ticketReservationForm, ScheduledMovie scheduledMovie);

    List<TicketReservation> findAll();

    Optional<TicketReservation> findById(TicketReservationId id);

    void addTicket(TicketReservationId ticketReservationId, TicketForm ticketForm) throws TicketReservationIdDoesNotExist;

    void deleteTicket(TicketReservationId ticketReservationId, TicketId ticketId) throws TicketReservationIdDoesNotExist, TicketIdDoesNotExist;

    void cancelReservation(TicketReservationId ticketReservationId) throws TicketReservationIdDoesNotExist;

    void confirmReservation(TicketReservationId ticketReservationId) throws TicketReservationIdDoesNotExist;

    Optional<TicketReservation> findByReservationStatus(ReservationStatus reservationStatus);

//    Optional<TicketReservation> makeReservation(TicketReservationForm ticketReservationForm, TicketForm ticketForm);

}
