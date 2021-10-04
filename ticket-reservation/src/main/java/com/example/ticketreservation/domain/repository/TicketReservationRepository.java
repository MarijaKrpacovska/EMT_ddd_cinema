package com.example.ticketreservation.domain.repository;

import com.example.ticketreservation.domain.models.ReservationStatus;
import com.example.ticketreservation.domain.models.TicketReservation;
import com.example.ticketreservation.domain.models.TicketReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketReservationRepository extends JpaRepository<TicketReservation, TicketReservationId> {

    Optional<TicketReservation> findByReservationStatus(ReservationStatus reservationStatus);
}
