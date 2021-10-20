package com.example.ticketreservation.xport.rest;

import com.example.sharedkernel.domain.money.Currency;
import com.example.ticketreservation.domain.exceptions.TicketReservationIdDoesNotExist;
import com.example.ticketreservation.domain.models.*;
import com.example.ticketreservation.domain.valueobjects.ScheduledMovie;
import com.example.ticketreservation.service.TicketReservationService;
import com.example.ticketreservation.service.forms.TicketForm;
import com.example.ticketreservation.service.forms.TicketReservationForm;
import com.example.ticketreservation.xport.client.ScheduledMovieClient;
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
    private final ScheduledMovieClient scheduledMovieClient;

    @GetMapping("/allReservations")
    public List<TicketReservation> allReservations() {
        return this.ticketReservationService.findAll();
    }

    @GetMapping("/allConfirmedReservations")
    public List<TicketReservation> allConfirmedReservations() {
        return this.ticketReservationService.findAllConfirmedReservations();
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

    @GetMapping("/findActiveReservation")
    public ResponseEntity<TicketReservation> findActiveReservation(){

        if(ticketReservationService.findByReservationStatus(ReservationStatus.ACTIVE).isPresent()) {
            return this.ticketReservationService.findByReservationStatus(ReservationStatus.ACTIVE)
                    .map(movie -> ResponseEntity.ok().body(movie))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }
        else {
            return null;
        }
    }

    //TODO: make simpler
    @PostMapping("/cancelActiveReservation")
    public ResponseEntity<TicketReservation> cancelActiveReservation(){
        if(ticketReservationService.findByReservationStatus(ReservationStatus.ACTIVE).isPresent()) {
            TicketReservation activeReservation = ticketReservationService.findByReservationStatus(ReservationStatus.ACTIVE).get();
            ticketReservationService.cancelReservation(activeReservation.getId());
            return this.ticketReservationService.findById(activeReservation.getId())
                    .map(movie -> ResponseEntity.ok().body(movie))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }
        else {
            return null;
        }
    }
    //todo: maybe add kafka for cancel scheduled movie and tickets

    @PostMapping("/cancelConfirmedReservation/{id}")
    public ResponseEntity<TicketReservation> cancelConfirmedReservation(@PathVariable String id){
        TicketReservation reservation = ticketReservationService.findById(new TicketReservationId(id)).orElseThrow(TicketReservationIdDoesNotExist::new);
        ticketReservationService.cancelConfirmedReservation(reservation.getId());
        return this.ticketReservationService.findById(reservation.getId())
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/confirmActiveReservation")
    public ResponseEntity<TicketReservation> confirmActiveReservation(){
        if(ticketReservationService.findByReservationStatus(ReservationStatus.ACTIVE).isPresent()) {
            TicketReservation activeReservation = ticketReservationService.findByReservationStatus(ReservationStatus.ACTIVE).get();
            ticketReservationService.confirmReservation(activeReservation.getId());
            return this.ticketReservationService.findById(activeReservation.getId())
                    .map(movie -> ResponseEntity.ok().body(movie))
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        }
        else {
            return null;
        }
    }

    @PostMapping("/confirmReservation/{id}")
    public ResponseEntity<TicketReservation> confirmReservationById(@PathVariable String id){
        TicketReservation reservation = ticketReservationService.findById(new TicketReservationId(id)).orElseThrow(TicketReservationIdDoesNotExist::new);
        ticketReservationService.confirmReservation(reservation.getId());
        return this.ticketReservationService.findById(reservation.getId())
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/cancelReservation/{id}")
    public ResponseEntity<TicketReservation> cancelReservationById(@PathVariable String id){
        TicketReservation reservation = ticketReservationService.findById(new TicketReservationId(id)).orElseThrow(TicketReservationIdDoesNotExist::new);
        ticketReservationService.cancelReservation(reservation.getId());
        return this.ticketReservationService.findById(reservation.getId())
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @GetMapping("/scheduledMovie/{id}")
    public ResponseEntity<ScheduledMovie> scheduledMovieTicket(@PathVariable String id){

        return scheduledMovieClient.findScheduledMovieById(id).map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

//    @PostMapping("/makeReservetionForScheduledMovie/{id}")
//    public ResponseEntity<TicketReservation> saveForScheduledMovie(@PathVariable String id, @RequestBody TicketReservationForm ticketReservationForm) {
//        ScheduledMovie scheduledMovie = scheduledMovieClient.findScheduledMovieById(id);
//
//        return this.ticketReservationService.makeReservation(ticketReservationForm)
//                .map(movie -> ResponseEntity.ok().body(movie))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

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

