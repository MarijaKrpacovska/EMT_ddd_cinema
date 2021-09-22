package com.example.ticketreservation.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.money.Currency;
import com.example.sharedkernel.domain.money.Money;
import com.example.ticketreservation.domain.valueobjects.Movie;
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

    public TicketReservation(Instant now, Currency currency) {
        super(TicketReservationId.randomId(TicketReservationId.class));
        this.reservationTime = now;
        this.currency = currency;
    }

    public Money total() {
        return tickets.stream().map(Ticket::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public Ticket addTicket(@NonNull Movie movie, int qty) {
        Objects.requireNonNull(movie,"movie must not be null");
        var ticket  = new Ticket(movie.getMovieId(), movie.getTicketPrice(),qty);
        tickets.add(ticket);
        return ticket;
    }

    public void removeTicket(@NonNull TicketId ticketId) {
        Objects.requireNonNull(ticketId,"Ticket must not be null");
        tickets.removeIf(v->v.getId().equals(ticketId));
    }

}
