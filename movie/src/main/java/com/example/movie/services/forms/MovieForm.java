package com.example.movie.services.forms;
import com.example.sharedkernel.domain.genre.Genre;
import com.example.sharedkernel.domain.time.MovieLength;
import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class MovieForm {

    private String name;

    @NonNull
    private MovieLength movieLength;

    private Instant publishDate;

    private Genre genre;

    private String description;

    @Valid
    @NotEmpty
    private List<ScheduledMovieForm> scheduledMovies = new ArrayList<>();

}
