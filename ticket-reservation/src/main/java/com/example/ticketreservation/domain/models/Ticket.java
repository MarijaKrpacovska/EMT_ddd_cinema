package com.example.ticketreservation.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.money.Money;
import com.example.ticketreservation.domain.valueobjects.MovieId;
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
    private MovieId movieId;

    private Ticket() {
        super(DomainObjectId.randomId(TicketId.class));
    }

    public Ticket(@NonNull MovieId movieId, @NonNull Money price, @NonNull SeatNumber seatNumber) {
        super(DomainObjectId.randomId(TicketId.class));
        this.movieId = movieId;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    public Money subtotal() {
        return price;
    }

}
