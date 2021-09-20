package com.example.ticketreservation.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.ticketreservation.domain.valueobjects.Money;
import com.example.ticketreservation.domain.valueobjects.SeatNumber;

import javax.persistence.*;

@Entity
@Table(name="ticket")
public class Ticket extends AbstractEntity<TicketId> {

    private boolean isBooked;

    private Money price;

    private SeatNumber seatNumber;

}
