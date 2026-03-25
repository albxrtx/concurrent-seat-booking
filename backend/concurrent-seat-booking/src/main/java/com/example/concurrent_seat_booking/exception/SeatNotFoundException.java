package com.example.concurrent_seat_booking.exception;

public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
