package com.example.sharedkernel.domain.events.ticketReservations;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class ReservationConfirmed extends DomainEvent {
    private String scheduledMovieId;
    private int quantity;

    public ReservationConfirmed(String topic) {
        super(TopicHolder.TOPIC_RESERVATION_CONFIRMED);
    }

    public ReservationConfirmed(String scheduledMovieId, int quantity) {
        super(TopicHolder.TOPIC_RESERVATION_CONFIRMED);
        this.scheduledMovieId = scheduledMovieId;
        this.quantity = quantity;
    }

}
