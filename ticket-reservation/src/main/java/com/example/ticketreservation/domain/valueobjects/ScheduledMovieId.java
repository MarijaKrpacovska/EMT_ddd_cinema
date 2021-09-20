package com.example.ticketreservation.domain.valueobjects;

import com.example.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ScheduledMovieId extends DomainObjectId {

    private ScheduledMovieId() {
        super(ScheduledMovieId.randomId(ScheduledMovieId.class).getId());
    }

    public ScheduledMovieId(String uuid) {
        super(uuid);
    }

}
