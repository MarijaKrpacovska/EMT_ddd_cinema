package com.example.ticketreservation.Service;

import com.example.sharedkernel.domain.time.MovieTime;
import com.example.ticketreservation.domain.exceptions.TicketReservationIdDoesNotExist;
import com.example.ticketreservation.domain.models.TicketReservation;
import com.example.ticketreservation.domain.models.TicketReservationId;
import com.example.ticketreservation.domain.valueobjects.*;
import com.example.ticketreservation.service.TicketReservationService;
import com.example.ticketreservation.service.forms.TicketForm;
import com.example.ticketreservation.service.forms.TicketReservationForm;
import com.example.ticketreservation.xport.client.MovieClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TicketReservationServiceTest {

    @Autowired
    private TicketReservationService ticketReservationService;

    @Autowired
    private MovieClient movieClient;


    private static ScheduledMovie newScheduledMovie(Money ticketPrice, int capacity, MovieTime start, MovieTime end, int sales) {
        ScheduledMovie scheduledMovie = new ScheduledMovie(ScheduledMovieId.randomId(ScheduledMovieId.class),capacity,ticketPrice, sales,start, end);
        return scheduledMovie;
    }

    @Test
    public void testMakeReservation() {

        TicketForm ticketForm = new TicketForm();
        ticketForm.setScheduledMovie(newScheduledMovie(new Money(Currency.MKD,30),40, new MovieTime(10,10),new MovieTime(10,10),10));

        TicketForm ticketForm1 = new TicketForm();
        ticketForm1.setScheduledMovie(newScheduledMovie(new Money(Currency.MKD,30),40, new MovieTime(10,10),new MovieTime(10,10),10));


        TicketReservationForm ticketReservationForm = new TicketReservationForm();
        ticketReservationForm.setCurrency(Currency.MKD);
        ticketReservationForm.setTickets(Arrays.asList(ticketForm,ticketForm1));

        TicketReservationId newTicketReservationId = ticketReservationService.makeReservation(ticketReservationForm);
        TicketReservation newTicketReservation = ticketReservationService.findById(newTicketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
        Assertions.assertEquals(newTicketReservation.total(),Money.valueOf(Currency.MKD,60));

    }

    @Test
    public void testMakeReservationWithRealData() {
        List<ScheduledMovie> scheduledMovies = movieClient.findAllScheduledMovies("f1c90020-b226-4281-9aff-aad18bce0270");
        ScheduledMovie sm1 = scheduledMovies.get(0);
        ScheduledMovie sm2 = scheduledMovies.get(1);

        TicketForm t1 = new TicketForm();
        t1.setScheduledMovie(sm1);

        TicketForm t2 = new TicketForm();
        t2.setScheduledMovie(sm2);

        TicketReservationForm ticketReservationForm = new TicketReservationForm();
        ticketReservationForm.setCurrency(Currency.MKD);
        ticketReservationForm.setTickets(Arrays.asList(t1,t2));

        TicketReservationId newTicketReservationId = ticketReservationService.makeReservation(ticketReservationForm);
        TicketReservation newTR = ticketReservationService.findById(newTicketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
    }



}

