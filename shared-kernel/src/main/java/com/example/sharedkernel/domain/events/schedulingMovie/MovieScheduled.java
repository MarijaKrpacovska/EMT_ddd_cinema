package com.example.sharedkernel.domain.events.schedulingMovie;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class MovieScheduled extends DomainEvent {

    private String movieId;

    public MovieScheduled() {
        super(TopicHolder.TOPIC_MOVIE_SCHEDULED);
    }

    public MovieScheduled(String movieId) {
        super(TopicHolder.TOPIC_MOVIE_SCHEDULED);
        this.movieId=movieId;
    }


}
