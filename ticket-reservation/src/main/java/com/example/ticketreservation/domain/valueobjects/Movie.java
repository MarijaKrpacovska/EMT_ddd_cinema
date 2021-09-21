//package com.example.ticketreservation.domain.valueobjects;
//
//import com.example.sharedkernel.domain.base.ValueObject;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Getter;
//
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import java.time.Instant;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//public class Movie implements ValueObject {
//
//    private MovieId movieId;
//
//    private String name;
//
//    private Instant publishDate;
//
//    private String description;
//
//    private Set<ScheduledMovie> scheduledMovies  = new HashSet<>();
//
//    private Movie() {
//        this.movieId=MovieId.randomId(MovieId.class);
//        this.name= "";
//        this.publishDate = Instant.now();
//        this.description = "";
//    }
//
//    @JsonCreator
//    private Movie(@JsonProperty("movieId") MovieId movieId,@JsonProperty("movieId")  String name,@JsonProperty("movieId")  Instant publishDate,@JsonProperty("movieId")  String description) {
//        this.movieId=movieId;
//        this.name= name;
//        this.publishDate = publishDate;
//        this.description = description;
//    }
//
//}
