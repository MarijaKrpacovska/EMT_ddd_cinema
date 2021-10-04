package com.example.ticketreservation.xport.rest;

import com.example.sharedkernel.domain.money.Currency;
import com.example.ticketreservation.domain.models.*;
import com.example.ticketreservation.service.TicketReservationService;
import com.example.ticketreservation.service.forms.TicketForm;
import com.example.ticketreservation.service.forms.TicketReservationForm;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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

    @GetMapping("/findReservation/{id}")
    public ResponseEntity<TicketReservation> findReservation(@PathVariable String id){
        return this.ticketReservationService.findById(new TicketReservationId(id))
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/findActiveReservation")
    public ResponseEntity<TicketReservation> findActiveReservation(){

        if(ticketReservationService.findByReservationStatus(ReservationStatus.ACTIVE).isPresent()) {
            return this.ticketReservationService.findByReservationStatus(ReservationStatus.ACTIVE)
                    .map(movie -> ResponseEntity.ok().body(movie))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }
        else {
            TicketReservationForm ticketReservationForm = new TicketReservationForm(Currency.MKD,null, Instant.now(),ReservationStatus.ACTIVE, PaymentMethod.CASH);
            return this.ticketReservationService.makeReservation(ticketReservationForm)
                    .map(movie -> ResponseEntity.ok().body(movie))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }
    }

//    @PostMapping("/makeReservetionForMovie")
//    public ResponseEntity<TicketReservation> saveReservation(@RequestBody TicketReservationForm ticketReservationForm) {
//        return this.ticketReservationService.makeReservation(ticketReservationForm)
//                .map(movie -> ResponseEntity.ok().body(movie))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

//    @PostMapping("/saveIfDoesntExist")
//    public ResponseEntity<TicketReservation> saveIfDoesntExist(@RequestBody TicketReservationForm ticketReservationForm) {
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

    @PostMapping("/deleteTicketFromReservation/{id}")
    public ResponseEntity<TicketReservation> deleteTicketFromReservation(@PathVariable String id, @RequestParam String ticketId) {
        ticketReservationService.deleteTicket(new TicketReservationId(id), new TicketId(ticketId));

        return this.ticketReservationService.findById(new TicketReservationId(id))
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}

