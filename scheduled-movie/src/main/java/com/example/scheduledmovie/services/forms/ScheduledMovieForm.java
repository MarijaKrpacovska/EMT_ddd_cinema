package com.example.scheduledmovie.services.forms;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ScheduledMovieForm {

    private int sales;

    //todo: fix input for videoTrailer url: kako da se vnese url shto raboti
    @NotNull
    private String startDate;

    @NotNull
    private String startTime;

    @NotNull
    private Money ticketPrice;

    @NotNull
    private String movieId;

    @JsonCreator
    public ScheduledMovieForm(@JsonProperty("sales") int sales,
                              @JsonProperty("startTime") String startTime,
                                @JsonProperty("startDate") String startDate,
                                 @JsonProperty("ticketPrice") Money ticketPrice,
                                 @JsonProperty("movieId") String movieId
    ){
        this.sales=sales;
        this.startTime=startTime;
        this.startDate=startDate;
        this.ticketPrice=ticketPrice;
        this.movieId=movieId;
    }

}
