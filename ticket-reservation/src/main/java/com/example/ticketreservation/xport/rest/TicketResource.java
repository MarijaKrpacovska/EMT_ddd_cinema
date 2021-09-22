package com.example.ticketreservation.xport.rest;

import com.example.ticketreservation.domain.models.TicketReservation;
import com.example.ticketreservation.service.TicketReservationService;
import com.example.ticketreservation.service.forms.TicketReservationForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketResource {

    private final TicketReservationService ticketReservationService;


    @PostMapping("/makeReservetion")
    public ResponseEntity<TicketReservation> save(@RequestBody TicketReservationForm ticketReservationForm) {
        return this.ticketReservationService.makeReservation(ticketReservationForm)
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}

