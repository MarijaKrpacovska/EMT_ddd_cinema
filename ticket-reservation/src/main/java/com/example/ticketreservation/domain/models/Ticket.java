package com.example.ticketreservation.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.ticketreservation.domain.valueobjects.Money;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovieId;
import com.example.ticketreservation.domain.valueobjects.SeatNumber;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="ticket")
@Getter
public class Ticket extends AbstractEntity<TicketId> {

    private boolean isBooked;

    private Money price;

    private SeatNumber seatNumber;

    @AttributeOverride(name = "id", column = @Column(name = "movie_id", nullable = false))
    private ScheduledMovieId scheduledMovieId;

    private Ticket() {
        super(DomainObjectId.randomId(TicketId.class));
    }

    public Ticket(@NonNull ScheduledMovieId scheduledMovieId, @NonNull Money price, @NonNull SeatNumber seatNumber) {
        super(DomainObjectId.randomId(TicketId.class));
        this.scheduledMovieId = scheduledMovieId;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    public Money subtotal() {
        return price;
    }

}
