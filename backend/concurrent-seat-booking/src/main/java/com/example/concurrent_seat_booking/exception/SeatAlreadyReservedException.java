package com.example.concurrent_seat_booking.exception;

public class SeatAlreadyReservedException extends RuntimeException {
    public SeatAlreadyReservedException(String errorMessage) {
        super(errorMessage);
    }
}
