package com.example.scheduledmovie.xport.rest;

import com.example.scheduledmovie.domain.models.ScheduledMovie;
import com.example.scheduledmovie.domain.models.ScheduledMovieId;
import com.example.scheduledmovie.services.ScheduledMovieService;
import com.example.scheduledmovie.services.forms.ScheduledMovieForm;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<ScheduledMovie> save(@RequestBody ScheduledMovieForm scheduledMovieForm) {
        return this.scheduledMovieService.save(scheduledMovieForm)
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}

