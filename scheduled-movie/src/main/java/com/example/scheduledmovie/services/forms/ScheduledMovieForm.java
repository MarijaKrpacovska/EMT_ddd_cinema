package com.example.scheduledmovie.services.forms;//package com.example.movie.services.forms;

import com.example.scheduledmovie.domain.valueobjects.Movie;
import com.example.scheduledmovie.domain.valueobjects.MovieId;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieTime;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ScheduledMovieForm {

    private int sales;

    @NotNull
    private MovieTime startTime;

    @NotNull
    private MovieTime endTime;

    @NotNull
    private Money ticketPrice;

    @NotNull
    private String movieId;

}
