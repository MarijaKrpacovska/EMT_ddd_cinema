package com.example.movie.xport.events;

import com.example.movie.domain.models.MovieId;
import com.example.movie.services.MovieService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieEventListener {

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

