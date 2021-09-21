package com.example.sharedkernel.domain.events.ticketReservations;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.Getter;

@Getter
public class TicketAdded extends DomainEvent {

    private String scheduledMovieId;

    public TicketAdded(String topic) {
        super(TopicHolder.TOPIC_TICKET_ADDED);
    }

    public TicketAdded(String scheduledMovieId) {
        super(TopicHolder.TOPIC_TICKET_ADDED);
        this.scheduledMovieId = scheduledMovieId;
    }
}
