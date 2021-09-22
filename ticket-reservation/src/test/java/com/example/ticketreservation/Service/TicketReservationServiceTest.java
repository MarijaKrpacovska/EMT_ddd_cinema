package com.example.ticketreservation.Service;

import com.example.sharedkernel.domain.genre.Genre;
import com.example.sharedkernel.domain.money.Currency;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieLength;
import com.example.sharedkernel.domain.time.MovieTime;
import com.example.sharedkernel.domain.time.UnitOfTime;
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


    private static Movie newMovie(String name, MovieLength movieLength, Genre genre, Instant publishDate, String description) {
        Movie movie = new Movie(MovieId.randomId(MovieId.class),name,movieLength,genre,publishDate,description, new Money(Currency.MKD,10),null);
        return movie;
    }

    @Test
    public void testMakeReservation() {

        TicketForm ticketForm = new TicketForm();
        ticketForm.setMovie(newMovie("movie",new MovieLength(10, UnitOfTime.min),Genre.action,Instant.now(),"desc"));

        TicketForm ticketForm1 = new TicketForm();
        ticketForm1.setMovie(newMovie("movie",new MovieLength(10, UnitOfTime.min),Genre.action,Instant.now(),"desc"));

        TicketReservationForm ticketReservationForm = new TicketReservationForm();
        ticketReservationForm.setCurrency(Currency.MKD);
        ticketReservationForm.setTickets(Arrays.asList(ticketForm,ticketForm1));

        TicketReservationId newTicketReservationId = ticketReservationService.makeReservation(ticketReservationForm);
        TicketReservation newTicketReservation = ticketReservationService.findById(newTicketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
        Assertions.assertEquals(newTicketReservation.total(), Money.valueOf(Currency.MKD,20));

    }

    @Test
    public void testMakeReservationWithRealData() {
        List<Movie> scheduledMovies = movieClient.findAll();
        Movie sm1 = scheduledMovies.get(0);
        Movie sm2 = scheduledMovies.get(1);

        TicketForm t1 = new TicketForm();
        t1.setMovie(sm1);

        TicketForm t2 = new TicketForm();
        t2.setMovie(sm2);

        TicketReservationForm ticketReservationForm = new TicketReservationForm();
        ticketReservationForm.setCurrency(Currency.MKD);
        ticketReservationForm.setTickets(Arrays.asList(t1,t2));

        TicketReservationId newTicketReservationId = ticketReservationService.makeReservation(ticketReservationForm);
        TicketReservation newTR = ticketReservationService.findById(newTicketReservationId).orElseThrow(TicketReservationIdDoesNotExist::new);
        Assertions.assertEquals(newTR.total(), Money.valueOf(Currency.MKD,8));

    }



}

