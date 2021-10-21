package com.example.scheduledmovie.xport.rest;

import com.example.scheduledmovie.domain.exceptions.ScheduledMovieIdDoesNotExistException;
import com.example.scheduledmovie.domain.models.ScheduledMovie;
import com.example.scheduledmovie.domain.models.ScheduledMovieId;
import com.example.scheduledmovie.domain.valueobjects.MovieId;
import com.example.scheduledmovie.services.ScheduledMovieService;
import com.example.scheduledmovie.services.forms.ScheduledMovieForm;
import com.example.sharedkernel.domain.money.Currency;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/scheduledMovie")
@AllArgsConstructor
public class ScheduledMovieResource {

    private final ScheduledMovieService scheduledMovieService;

    @GetMapping
    public List<ScheduledMovie> getAll() {
        return scheduledMovieService.findAll();
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<ScheduledMovie> findById(@PathVariable String id) {
        return this.scheduledMovieService.findById(new ScheduledMovieId(id))
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findAllByMovieId/{id}")
    public List<ScheduledMovie> findAllByMovieId(@PathVariable String id) {
        return this.scheduledMovieService.findAllByMovieId(new MovieId(id));
    }

//    @PostMapping("/add")
//    public ResponseEntity<ScheduledMovie> save(@RequestBody ScheduledMovieForm scheduledMovieForm) {
//        return this.scheduledMovieService.save(new ScheduledMovieForm(0, new MovieTime(0,0, LocalDate.of(2021,1,1)),new MovieTime(0,0,LocalDate.of(2021,1,1)), new Money(Currency.MKD,1), "9a78fd3e-9caf-490a-a1d4-c91852494c05"))
//                .map(movie -> ResponseEntity.ok().body(movie))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

    @PostMapping("/addScheduledMovie")
    public ResponseEntity<ScheduledMovie> saveScheduledMovie(@RequestBody ScheduledMovieForm scheduledMovieForm) {
        return this.scheduledMovieService.save(scheduledMovieForm)
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //TODO: simplify
    @PostMapping("/cancelScheduledMovie/{id}")
    public ResponseEntity<ScheduledMovie> cancelConfirmedReservation(@PathVariable String id){
        ScheduledMovie scheduledMovie = scheduledMovieService.findById(new ScheduledMovieId(id)).orElseThrow(ScheduledMovieIdDoesNotExistException::new);
        scheduledMovieService.cancelScheduledMovie(scheduledMovie.getId());
        return this.scheduledMovieService.findById(scheduledMovie.getId())
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/rescheduleMovie/{id}")
    public ResponseEntity<ScheduledMovie> rescheduleConfirmedReservation(@PathVariable String id, @RequestParam String time, @RequestParam String date){
        return this.scheduledMovieService.rescheduleMovie(new ScheduledMovieId(id),time,date)
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}

