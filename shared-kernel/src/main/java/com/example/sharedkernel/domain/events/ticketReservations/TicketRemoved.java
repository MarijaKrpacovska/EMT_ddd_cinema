package com.example.sharedkernel.domain.events.ticketReservations;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.Getter;

@Getter
public class TicketRemoved extends DomainEvent {

    private String scheduledMovieId;

    public TicketRemoved(String topic) {
        super(TopicHolder.TOPIC_TICKET_REMOVED);
    }

    public TicketRemoved(String scheduledMovieId) {
        super(TopicHolder.TOPIC_TICKET_REMOVED);
        this.scheduledMovieId = scheduledMovieId;
    }
}

