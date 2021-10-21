package com.example.scheduledmovie.xport.events;

import com.example.scheduledmovie.domain.models.ScheduledMovieId;
import com.example.scheduledmovie.services.ScheduledMovieService;
import com.example.scheduledmovie.services.forms.ScheduledMovieForm;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.ticketReservations.ReservationCanceled;
import com.example.sharedkernel.domain.events.ticketReservations.ReservationConfirmed;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduledMovieEventListener {

    private final ScheduledMovieService movieService;

    @KafkaListener(topics= TopicHolder.TOPIC_RESERVATION_CONFIRMED, groupId = "cinema")
    public void consumeReservationConfirmedEvent(String jsonMessage) {
        System.out.println(jsonMessage);
        try {
            ReservationConfirmed event = DomainEvent.fromJson(jsonMessage,ReservationConfirmed.class);
            System.out.print(event.getQuantity());
            movieService.reservationConfirmed(new ScheduledMovieId(event.getScheduledMovieId()),event.getQuantity());
        } catch (Exception e){
            System.out.print(e);
        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_RESERVATION_CANCELED, groupId = "cinema")
    public void consumeReservationCanceledEvent(String jsonMessage) {
        System.out.println(jsonMessage);
        try {
            ReservationCanceled event = DomainEvent.fromJson(jsonMessage,ReservationCanceled.class);
            System.out.print(event.getQuantity());
            movieService.reservationCanceled(new ScheduledMovieId(event.getScheduledMovieId()),event.getQuantity());
        } catch (Exception e){
            System.out.print(e);
        }

    }

}

