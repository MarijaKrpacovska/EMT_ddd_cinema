package com.example.sharedkernel.domain.events.ticketReservations;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.Getter;

@Getter
public class TicketAdded extends DomainEvent {

    private String movieId;
    private MovieTime movieTime;

    public TicketAdded(String topic) {
        super(TopicHolder.TOPIC_TICKET_ADDED);
    }

    public TicketAdded(String movieId,MovieTime movieTime) {
        super(TopicHolder.TOPIC_TICKET_ADDED);
        this.movieId = movieId;
        this.movieTime=movieTime;
    }
}
