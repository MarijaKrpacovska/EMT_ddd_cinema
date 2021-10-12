package com.example.movie.xport.events;

import com.example.movie.domain.models.MovieId;
import com.example.movie.services.MovieService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.schedulingMovie.MovieScheduled;
import com.example.sharedkernel.domain.events.schedulingMovie.ScheduledMovieCanceled;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieEventListener {

    private final MovieService movieService;

    @KafkaListener(topics= TopicHolder.TOPIC_MOVIE_SCHEDULED, groupId = "cinema")
    public void consumeMovieScheduledEvent(String jsonMessage) {
        try {
            MovieScheduled event = DomainEvent.fromJson(jsonMessage,MovieScheduled.class);
            movieService.scheduledMovieAdded(new MovieId(event.getMovieId()));
        } catch (Exception e){

        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_SCHEDULED_MOVIE_CANCELED, groupId = "cinema")
    public void consumeScheduledMovieCanceledEvent(String jsonMessage) {
        try {
            ScheduledMovieCanceled event = DomainEvent.fromJson(jsonMessage,ScheduledMovieCanceled.class);
            movieService.scheduledMovieRemoved(new MovieId(event.getMovieId()));
        } catch (Exception e){

        }

    }

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

