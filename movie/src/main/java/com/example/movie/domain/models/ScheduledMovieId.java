package com.example.movie.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;

public class ScheduledMovieId extends DomainObjectId {
    private ScheduledMovieId() {
        super(ScheduledMovieId.randomId(ScheduledMovieId.class).getId());
    }

    public ScheduledMovieId(@NonNull String uuid) {
        super(uuid);
    }
}
