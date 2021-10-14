package com.example.movie.xport.rest;

import com.example.movie.domain.exceptions.MovieIdDoesNotExistException;
import com.example.movie.domain.models.Movie;
import com.example.movie.domain.models.MovieId;
import com.example.movie.services.MovieService;
import com.example.movie.services.forms.MovieForm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/movie")
@AllArgsConstructor
public class MovieResource {

    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAll() {
        return movieService.findAll();
    }

    @GetMapping("/pagination")
    public List<Movie> findAllWithPagination(Pageable pageable) {
        return this.movieService.findAllWithPagination(pageable).getContent();
    }

    @GetMapping("/moviePagination")
    public Page<Movie> findAllPagination(Pageable pageable) {
        return this.movieService.findAllWithPagination(pageable);
    }


//    @GetMapping("/scheduledMovie/{id}")
//    public ScheduledMovie getMovieAtCertainTime(@PathVariable MovieId id,
//                                                @RequestParam int hour,
//                                                @RequestParam int minutes) {
//        Movie movie = movieService.findById(id).orElseThrow(MovieIdDoesNotExistException::new);
//        return movie.findScheduledMovie(hour,minutes);
//    }
//
//    @GetMapping("/scheduledMovies/{id}")
//    public List<ScheduledMovie> getAllScheduledMovies(@PathVariable String id) {
//        Movie movie = movieService.findById(new MovieId(id)).orElseThrow(MovieIdDoesNotExistException::new);
//        return movie.findTimeOptions();
//    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Movie> findById(@PathVariable String id) {
        return this.movieService.findById(new MovieId(id))
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> save(@RequestBody MovieForm movieForm) {
        return this.movieService.addMovie(movieForm)
                .map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


//    @PostMapping("/scheduleMovie/{id}")
//    public ResponseEntity<Movie> scheduleMovie(@PathVariable String id, @RequestBody ScheduledMovieForm scheduledMovieForm) {
//        return this.movieService.addScheduledMovie(new MovieId(id),scheduledMovieForm)
//                .map(movie -> ResponseEntity.ok().body(movie))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

}

