package com.example.ticketreservation.service.forms;

import com.example.ticketreservation.domain.valueobjects.Movie;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovie;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class TicketForm {

    @NotNull
    private Movie movie;

    @Min(1)
    private int qty=1;

}
