package com.example.sharedkernel.domain.events.ticketReservations;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class ReservationCanceled extends DomainEvent {

    //Celta na ovoj nastan e azhuriranje na vrednosta na sales, vo soodvetniot scheduledMovie, za sekoja otkazhana rezervacija
    //Ovoj event se sluchuva samo koga rezervacijata koja prethodno bila potvrdena, e otkazhana, bidejki rezervaciite koi nikogash ne bile potvrdeni, nikogash ne ni bile izbroeni vo sales, pa nema potreba od namaluvanje na sales
    private String scheduledMovieId;
    private int quantity;

    public ReservationCanceled() {
        super(TopicHolder.TOPIC_RESERVATION_CANCELED);
    }

    public ReservationCanceled(String scheduledMovieId, int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_CANCELED);
        this.scheduledMovieId = scheduledMovieId;
        this.quantity = quantity;
    }

}
