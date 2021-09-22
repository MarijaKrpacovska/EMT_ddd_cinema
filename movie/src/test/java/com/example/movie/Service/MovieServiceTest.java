package com.example.movie.Service;

import com.example.movie.domain.exceptions.MovieIdDoesNotExistException;
import com.example.movie.domain.models.Movie;
import com.example.movie.domain.models.MovieId;
import com.example.movie.domain.valueobjects.*;
import com.example.movie.services.MovieService;
import com.example.movie.services.forms.MovieForm;
import com.example.movie.services.forms.ScheduledMovieForm;
import com.example.sharedkernel.domain.time.MovieLength;
import com.example.sharedkernel.domain.time.MovieTime;
import com.example.sharedkernel.domain.time.UnitOfTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void testMovieService() {

        ScheduledMovieForm scheduledMovieForm = new ScheduledMovieForm();
        scheduledMovieForm.setCapacity(20);
        scheduledMovieForm.setSales(10);
        scheduledMovieForm.setStartTime(new MovieTime(4,30));
        scheduledMovieForm.setEndTime(new MovieTime(6,30));
        scheduledMovieForm.setTicketPrice(new Money(Currency.MKD,20));

        ScheduledMovieForm scheduledMovieForm1 = new ScheduledMovieForm();
        scheduledMovieForm1.setCapacity(20);
        scheduledMovieForm1.setSales(10);
        scheduledMovieForm1.setStartTime(new MovieTime(4,30));
        scheduledMovieForm1.setEndTime(new MovieTime(6,30));
        scheduledMovieForm1.setTicketPrice(new Money(Currency.MKD,20));


        MovieForm movieForm = new MovieForm(new MovieLength(40, UnitOfTime.min));
        movieForm.setScheduledMovies(Arrays.asList(scheduledMovieForm,scheduledMovieForm1));

        MovieId newMovieId = movieService.addMovie(movieForm);
        Movie newMovie = movieService.findById(newMovieId).orElseThrow(MovieIdDoesNotExistException::new);

    }


}

