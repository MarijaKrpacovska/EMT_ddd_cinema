package com.example.ticketreservation.xport.rest;

import com.example.ticketreservation.domain.models.TicketReservation;
import com.example.ticketreservation.domain.models.TicketReservationId;
import com.example.ticketreservation.service.TicketReservationService;
import com.example.ticketreservation.service.forms.TicketForm;
import com.example.ticketreservation.service.forms.TicketReservationForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketResource {

    private final TicketReservationService ticketReservationService;

    @GetMapping("/allReservations")
    public List<TicketReservation> allReservations() {
        return this.ticketReservationService.findAll();
    }

    @PostMapping("/makeReservetion")
    public ResponseEntity<TicketReservation> save(@RequestBody TicketReservationForm ticketReservationForm) {
        return this.ticketReservationService.makeReservation(ticketReservationForm)
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

//    @PostMapping("/makeReservetionForMovie")
//    public ResponseEntity<TicketReservation> saveReservation(@RequestBody TicketReservationForm ticketReservationForm) {
//        return this.ticketReservationService.makeReservation(ticketReservationForm)
//                .map(movie -> ResponseEntity.ok().body(movie))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

    @PostMapping("/addTicketToReservation/{id}")
    public ResponseEntity<TicketReservation> addTicketToReservation(@PathVariable String id, @RequestBody TicketForm ticketForm) {
        ticketReservationService.addTicket(new TicketReservationId(id), ticketForm);

        return this.ticketReservationService.findById(new TicketReservationId(id))
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}

