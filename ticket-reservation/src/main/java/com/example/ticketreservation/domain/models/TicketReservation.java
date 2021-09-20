package com.example.ticketreservation.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.ticketreservation.domain.valueobjects.Money;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="ticket_reservation")
public class TicketReservation extends AbstractEntity<TicketReservationId> {

    private LocalDateTime reservationTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private Money total;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Ticket> tickets;

    public TicketReservation() {
    }
}
