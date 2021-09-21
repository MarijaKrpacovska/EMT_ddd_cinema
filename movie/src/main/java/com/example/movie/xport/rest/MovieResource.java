package com.example.movie.xport.rest;

import com.example.movie.domain.exceptions.MovieIdDoesNotExistException;
import com.example.movie.domain.models.Movie;
import com.example.movie.domain.models.MovieId;
import com.example.movie.domain.models.ScheduledMovie;
import com.example.movie.services.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@AllArgsConstructor
public class MovieResource {

    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAll() {
        return movieService.findAll();
    }

    @GetMapping("/scheduledMovie/{id}")
    public ScheduledMovie getMovieAtCertainTime(@PathVariable MovieId id,
                                                @RequestParam int hour,
                                                @RequestParam int minutes) {
        Movie movie = movieService.findById(id).orElseThrow(MovieIdDoesNotExistException::new);
        return movie.findScheduledMovie(hour,minutes);
    }

    @GetMapping("/scheduledMovies/{id}")
    public List<ScheduledMovie> getAllScheduledMovies(@PathVariable String id) {
        Movie movie = movieService.findById(new MovieId(id)).orElseThrow(MovieIdDoesNotExistException::new);
        return movie.findTimeOptions();
    }

}

