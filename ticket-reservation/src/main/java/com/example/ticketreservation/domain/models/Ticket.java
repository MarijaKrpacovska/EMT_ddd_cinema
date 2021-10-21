package com.example.ticketreservation.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.money.Money;
import com.example.ticketreservation.domain.valueobjects.MovieId;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovie;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovieId;
import lombok.Getter;
import lombok.NonNull;
import javax.persistence.*;

@Entity
@Table(name="ticket")
@Getter
public class Ticket extends AbstractEntity<TicketId> {

    //price e od tipot Money so cel da se zapazi seprisutniot jazik
    private Money price;

    //dodadov atribut quantity, koj oznachuva kolkavo kolichestvo na bileti se narachuva
    @Column(name = "qty", nullable = false)
    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name = "scheduledMovieId", nullable = false))
    private ScheduledMovieId scheduledMovieId;

    private Ticket() {
        super(DomainObjectId.randomId(TicketId.class));
    }

    public Ticket(@NonNull ScheduledMovieId scheduledMovieId, Money price, int qty) {
        super(DomainObjectId.randomId(TicketId.class));
        this.scheduledMovieId = scheduledMovieId;
        this.price = price;
        this.quantity=qty;
    }

    public Money subtotal() {
        return price;
    }

}
