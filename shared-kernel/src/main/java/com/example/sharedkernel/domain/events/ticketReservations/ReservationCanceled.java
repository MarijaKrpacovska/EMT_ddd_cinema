package com.example.sharedkernel.domain.events.ticketReservations;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class ReservationCanceled extends DomainEvent {
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
