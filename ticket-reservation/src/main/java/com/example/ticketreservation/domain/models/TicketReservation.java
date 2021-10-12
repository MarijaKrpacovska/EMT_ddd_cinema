package com.example.ticketreservation.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.money.Currency;
import com.example.sharedkernel.domain.money.Money;
import com.example.ticketreservation.domain.valueobjects.Movie;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovie;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="ticket_reservation")
@Getter
public class TicketReservation extends AbstractEntity<TicketReservationId> {

    private Instant reservationTime;

    @Column(name="ticket_reservation_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Ticket> tickets = new HashSet<>();

    public TicketReservation() {
        super(TicketReservationId.randomId(TicketReservationId.class));
    }

    public TicketReservation(Instant now, Currency currency, ReservationStatus reservationStatus, PaymentMethod paymentMethod) {
        super(TicketReservationId.randomId(TicketReservationId.class));
        this.reservationTime = now;
        this.currency = currency;
        this.reservationStatus = reservationStatus;
        this.paymentMethod = paymentMethod;
    }

    public Money total() {
        return tickets.stream().map(Ticket::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public Ticket addTicket(@NonNull ScheduledMovie scheduledMovie, int qty) {
        Objects.requireNonNull(scheduledMovie,"movie must not be null");
        var ticket  = new Ticket(scheduledMovie.getId(), scheduledMovie.getTicketsPrice(),qty);
        tickets.add(ticket);
        return ticket;
    }

    public void removeTicket(@NonNull TicketId ticketId) {
        Objects.requireNonNull(ticketId,"Ticket must not be null");
        tickets.removeIf(v->v.getId().equals(ticketId));
    }

    //TODO: add time logic
    public void cancel() {
        if(reservationStatus.equals(ReservationStatus.ACTIVE))
            reservationStatus = ReservationStatus.CANCELED;
        else if(reservationStatus.equals(ReservationStatus.CONFIRMED)){
            reservationStatus = ReservationStatus.CANCELED;
        }
    }

    public void confirm() {
        reservationStatus = ReservationStatus.CONFIRMED;
    }

}
