package com.example.ticketreservation.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class SeatNumber implements ValueObject {
    private final int seatNumber;

    private final int rowNumber;

    private final int columnNumber;

    public SeatNumber() {
        this.seatNumber = 0;
        this.rowNumber = 0;
        this.columnNumber = 0;
    }

    public SeatNumber(int seatNumber, int rowNumber, int columnNumber) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }


}
