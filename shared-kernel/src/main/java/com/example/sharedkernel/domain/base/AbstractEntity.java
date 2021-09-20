package com.example.sharedkernel.domain.base;

import lombok.Getter;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
public class AbstractEntity<ID extends DomainObjectId> {

    @EmbeddedId
    private ID id;
}

