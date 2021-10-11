package com.example.scheduledmovie.xport.events;

import com.example.scheduledmovie.domain.models.ScheduledMovieId;
import com.example.scheduledmovie.services.ScheduledMovieService;
import com.example.scheduledmovie.services.forms.ScheduledMovieForm;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.ticketReservations.ReservationConfirmed;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduledMovieEventListener {

    private final ScheduledMovieService movieService;

    @KafkaListener(topics= TopicHolder.TOPIC_RESERVATION_CONFIRMED, groupId = "cinema")
    public void consumeReservationConfrmedEvent(String jsonMessage) {
        try {
            ReservationConfirmed event = DomainEvent.fromJson(jsonMessage,ReservationConfirmed.class);
            movieService.reservationConfirmed(new ScheduledMovieId(event.getScheduledMovieId()),event.getQuantity());
        } catch (Exception e){

        }

    }

//    @KafkaListener(topics= TopicHolder.TOPIC_RESERVATION_CANCELED, groupId = "cinema")
//    public void consumeReservationCanceledEvent(String jsonMessage) {
//        try {
//            ReservationCanceled event = DomainEvent.fromJson(jsonMessage,ReservationCanceled.class);
//            movieService.reservationCanceled(new ScheduledMovieId(event.getScheduledMovieId()),event.getQuantity());
//        } catch (Exception e){
//
//        }
//
//    }
}

