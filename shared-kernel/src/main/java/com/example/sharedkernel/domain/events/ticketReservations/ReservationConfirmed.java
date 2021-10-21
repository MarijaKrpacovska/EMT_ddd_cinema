package com.example.sharedkernel.domain.events.ticketReservations;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class ReservationConfirmed extends DomainEvent {

    //Celta na ovoj nastan e azhuriranje na vrednosta na sales, vo soodvetniot scheduledMovie, za sekoja potvrdena rezervacija
    //Ovoj event se sluchuva samo koga rezervacijata e potvrdena (nejziniot status e confirmed), a ne koga e kreirana, bidejki ne sekoga kreirana rezervacija ponatamu ke bide potvrdena
    private String scheduledMovieId;
    private int quantity;

    public ReservationConfirmed() {
        super(TopicHolder.TOPIC_RESERVATION_CONFIRMED);
    }

    public ReservationConfirmed(String scheduledMovieId, int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_CONFIRMED);
        this.scheduledMovieId = scheduledMovieId;
        this.quantity = quantity;
    }

}
