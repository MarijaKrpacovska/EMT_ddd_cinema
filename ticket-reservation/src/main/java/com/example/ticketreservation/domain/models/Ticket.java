package com.example.ticketreservation.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.money.Money;
import com.example.ticketreservation.domain.valueobjects.MovieId;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name="ticket")
@Getter
public class Ticket extends AbstractEntity<TicketId> {

    //price e od tipot Money so cel da se zapazi seprisutniot jazik
    private Money price;

    @Column(name = "qty", nullable = false)
    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name = "movie_id", nullable = false))
    private MovieId movieId;

    private Ticket() {
        super(DomainObjectId.randomId(TicketId.class));
    }

    public Ticket(@NonNull MovieId movieId, @NonNull Money price, int qty) {
        super(DomainObjectId.randomId(TicketId.class));
        this.movieId = movieId;
        this.price = price;
        this.quantity=qty;
    }

    public Money subtotal() {
        return price;
    }

}
