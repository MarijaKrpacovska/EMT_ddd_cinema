package com.example.scheduledmovie.xport.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduledMovieEventListener {

//    private final MovieService movieService;
//
//    @KafkaListener(topics= TopicHolder.TOPIC_TICKET_ADDED, groupId = "movie")
//    public void consumeOrderItemCreatedEvent(String jsonMessage) {
//        try {
//            TicketAdded event = DomainEvent.fromJson(jsonMessage,TicketAdded.class);
//            movieService.ticketAdded(new MovieId(event.getMovieId()),event.getMovieTime());
//        } catch (Exception e){
//
//        }
//
//    }
//
//    @KafkaListener(topics= TopicHolder.TOPIC_TICKET_REMOVED, groupId = "movie")
//    public void consumeOrderItemRemovedEvent(String jsonMessage) {
//        try {
//            TicketRemoved event = DomainEvent.fromJson(jsonMessage,TicketRemoved.class);
//            movieService.ticketRemoved(new MovieId(event.getMovieId()),event.getMovieTime());
//        } catch (Exception e){
//
//        }
//
//    }
}

