package com.example.movie.domain.models;

import com.example.movie.domain.valueobjects.Image;
import com.example.movie.domain.valueobjects.Rating;
import com.example.movie.domain.valueobjects.Video;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.genre.Genre;
import com.example.sharedkernel.domain.money.Money;
import com.example.sharedkernel.domain.time.MovieLength;
import com.example.sharedkernel.domain.time.MovieTime;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name="movie")
@Getter
public class Movie extends AbstractEntity<MovieId> {

    private String name;

    //Za atributot movieLength, definirav nov tip, MovieLength, so cel da se zapazi seprisutniot jazik
    private MovieLength movieLength;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    /*Za atributot PublishDate ja koristam klasata LocalDate, bidejki ovoj atribut ja pretstavuva datata na objavuvanje na filmot,
    pa smetav deka LocalDate klasata e najsoodvetna za ova*/
    private LocalDate publishDate;

    //Za atributot description, ja zgolemiv dolzhinata koja atributot mozhe da ja primi, bidejki opisot na film mozhe da bide i podolg od inicijalnite 255 karakteri.
    @Column(name = "description", length = 1024)
    private String description;

    //Za atributot rating, definirav nov tip, Rating, so cel da se zapazi seprisutniot jazik
    private Rating rating;

    //Za atributot moviePoster, definirav nov tip, Image, so cel da se zapazi seprisutniot jazik
    //Pritoa, gi preimenuvav kolonite vo "moviePosterUrl" i "moviePosterType"
    @AttributeOverrides({
            @AttributeOverride(name="imageUrl", column = @Column(name="moviePosterUrl")),
            @AttributeOverride(name="imageType", column = @Column(name="moviePosterType"))
    })
    private Image moviePoster;

    //Za atributot movieAdvertisementImage, go koristam istiot tip, Image, so cel da se zapazi seprisutniot jazik
    //Pritoa, gi preimenuvav kolonite vo "movieAdvertisementUrl" i "movieAdvertisementType"
    @AttributeOverrides({
            @AttributeOverride(name="imageUrl", column = @Column(name="movieAdvertisementUrl")),
            @AttributeOverride(name="imageType", column = @Column(name="movieAdvertisementType"))
    })
    private Image movieAdvertisementImage;

    //Za atributot movieTrailer, definirav nov tip, Video, so cel da se zapazi seprisutniot jazik
    private Video movieTrailer;

    //Reshiv da chuvam i podatok za toa kolku pati filmot bil scheduled.
    private int numberOfTimesScheduled;

//    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
//    private Set<ScheduledMovie> scheduledMovies  = new HashSet<>();

    private Movie() {
        super(MovieId.randomId(MovieId.class));
    }

    public Movie(String name, MovieLength movieLength, Genre genre, LocalDate publishDate, String description, Image moviePoster, Image movieAdvertisementImage, Video movieTrailer, int numberOfTimesScheduled, Rating rating) {
        super(MovieId.randomId(MovieId.class));
        this.name = name;
        this.movieLength = movieLength;
        this.genre=genre;
        this.publishDate=publishDate;
        this.description= description;
        this.moviePoster=moviePoster;
        this.movieAdvertisementImage=movieAdvertisementImage;
        this.movieTrailer=movieTrailer;
        this.numberOfTimesScheduled=numberOfTimesScheduled;
        this.rating=rating;
    }

    public static Movie build(String name, MovieLength movieLength, Genre genre, LocalDate publishDate, String description, Image moviePoster, Image movieAdvertisementImage, Video movieTrailer, int numberOfTimesScheduled, Rating rating) {// Set<ScheduledMovie> scheduledMovieSet
        Movie m = new Movie();
        m.name=name;
        m.movieLength=movieLength;
        m.genre=genre;
        m.publishDate=publishDate;
        m.description=description;
        m.movieTrailer=movieTrailer;
        m.moviePoster=moviePoster;
        m.movieAdvertisementImage=movieAdvertisementImage;
        m.numberOfTimesScheduled=numberOfTimesScheduled;
        m.rating=rating;
        return m;
    }

    public void addTimesScheduled() {
        this.numberOfTimesScheduled = this.numberOfTimesScheduled + 1;
    }

    public void decreaseTimesScheduled() {
        this.numberOfTimesScheduled = this.numberOfTimesScheduled - 1;
    }

    public void rate(double rating){ this.rating = this.rating.calculate(rating); }

}
