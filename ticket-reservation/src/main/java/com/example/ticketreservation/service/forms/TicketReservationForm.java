package com.example.ticketreservation.service.forms;

import com.example.ticketreservation.domain.valueobjects.Currency;
import com.sun.istack.NotNull;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketReservationForm {
    @NotNull
    private Currency currency;

    @Valid
    @NotEmpty
    private List<TicketForm> tickets = new ArrayList<>();

}
