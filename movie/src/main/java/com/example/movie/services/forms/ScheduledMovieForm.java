package com.example.movie.services.forms;

import com.example.movie.domain.valueobjects.Money;
import com.example.sharedkernel.domain.time.MovieTime;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ScheduledMovieForm {

    @NotNull
    private Money ticketPrice;

    private int sales;

    @NotNull
    private MovieTime startTime;

    @NotNull
    private MovieTime endTime;

    @Min(1)
    private int capacity = 1;

}
