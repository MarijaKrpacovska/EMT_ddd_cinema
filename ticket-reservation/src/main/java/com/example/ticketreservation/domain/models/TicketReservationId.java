package com.example.ticketreservation.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

public class TicketReservationId extends DomainObjectId {
    private TicketReservationId() {
        super(TicketReservationId.randomId(TicketReservationId.class).getId());
    }

    public TicketReservationId(@NonNull String uuid) {
        super(uuid);
    }
}
