package com.example.ticketreservation.Service;

import com.example.ticketreservation.domain.exceptions.TicketReservationIdDoesNotExist;
import com.example.ticketreservation.domain.models.TicketReservation;
import com.example.ticketreservation.domain.models.TicketReservationId;
import com.example.ticketreservation.domain.valueobjects.Currency;
import com.example.ticketreservation.domain.valueobjects.Money;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovie;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovieId;
import com.example.ticketreservation.service.TicketReservationService;
import com.example.ticketreservation.service.forms.TicketForm;
import com.example.ticketreservation.service.forms.TicketReservationForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class TicketReservationServiceTest {

    @Autowired
    private TicketReservationService ticketReservationService;



    private static ScheduledMovie newScheduledMovie(Money ticketPrice, int capacity) {
        ScheduledMovie scheduledMovie = new ScheduledMovie(ScheduledMovieId.randomId(ScheduledMovieId.class),capacity,ticketPrice);
        return scheduledMovie;
    }

    @Test
    public void testMakeReservation() {

        TicketForm ticketForm = new TicketForm();
        ticketForm.setScheduledMovie(newScheduledMovie(new Money(Currency.MKD,30),40));

        TicketForm ticketForm1 = new TicketForm();
        ticketForm1.setScheduledMovie(newScheduledMovie(new Money(Currency.MKD,30),60));


        TicketReservationForm ticketReservationForm = new TicketReservationForm();
        ticketReservationForm.setCurrency(Currency.MKD);
        ticketReservationForm.setTickets(Arrays.asList(ticketForm,ticketForm1));

        TicketReservationId newTicketReservationId = ticketReservationService.makeReservation(ticketReservationForm);
        TicketReservation newTicketReservation = ticketReservationService.findById(newTicketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
        Assertions.assertEquals(newTicketReservation.total(),Money.valueOf(Currency.MKD,60));

    }


}

