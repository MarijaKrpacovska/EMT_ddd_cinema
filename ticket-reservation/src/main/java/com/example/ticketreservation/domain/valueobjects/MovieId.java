package com.example.ticketreservation.domain.valueobjects;

import com.example.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class MovieId extends DomainObjectId {
    private MovieId() {
        super(MovieId.randomId(MovieId.class).getId());
    }

    public MovieId(String uuid) {
        super(uuid);
    }

}
