package com.example.sharedkernel.domain.events.ticketReservations;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.Getter;

@Getter
public class TicketRemoved extends DomainEvent {

    private String movieId;
    private MovieTime movieTime;

    public TicketRemoved(String topic) {
        super(TopicHolder.TOPIC_TICKET_REMOVED);
    }

    public TicketRemoved(String movieId,MovieTime movieTime) {
        super(TopicHolder.TOPIC_TICKET_REMOVED);
        this.movieId = movieId;
        this.movieTime = movieTime;
    }
}

