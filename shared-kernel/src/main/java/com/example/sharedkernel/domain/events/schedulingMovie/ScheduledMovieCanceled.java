package com.example.sharedkernel.domain.events.schedulingMovie;

import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import lombok.Getter;

@Getter
public class ScheduledMovieCanceled extends DomainEvent {

    private String movieId;

    public ScheduledMovieCanceled() {
        super(TopicHolder.TOPIC_SCHEDULED_MOVIE_CANCELED);
    }

    public ScheduledMovieCanceled(String movieId) {
        super(TopicHolder.TOPIC_SCHEDULED_MOVIE_CANCELED);
        this.movieId=movieId;
    }


}
