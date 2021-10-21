package com.example.ticketreservation.service.forms;

import com.example.sharedkernel.domain.money.Currency;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieTime;
import com.example.ticketreservation.domain.models.PaymentMethod;
import com.example.ticketreservation.domain.models.ReservationStatus;
import com.example.ticketreservation.domain.models.Ticket;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovieId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TicketReservationForm {
    @NotNull
    private Currency currency;

    @Valid
    private Set<TicketForm> tickets = new HashSet<>();

    private PaymentMethod paymentMethod;

    @JsonCreator
    public TicketReservationForm(@JsonProperty("currency") Currency currency,
                                 @JsonProperty("tickets") Set<TicketForm> tickets,
                                 @JsonProperty("paymentMethod") PaymentMethod paymentMethod
                                 ){
        this.currency = currency;
        this.tickets = tickets;
        this.paymentMethod=paymentMethod;
    }
}
