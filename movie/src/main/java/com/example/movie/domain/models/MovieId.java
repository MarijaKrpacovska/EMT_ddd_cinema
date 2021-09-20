package com.example.movie.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

public class MovieId extends DomainObjectId {
    private MovieId() {
        super(MovieId.randomId(MovieId.class).getId());
    }

    public MovieId(@NonNull String uuid) {
        super(uuid);
    }

}
