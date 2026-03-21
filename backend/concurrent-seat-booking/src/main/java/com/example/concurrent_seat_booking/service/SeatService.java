package com.example.concurrent_seat_booking.service;

import org.springframework.stereotype.Service;

import com.example.concurrent_seat_booking.model.Seat;
import com.example.concurrent_seat_booking.repository.SeatRepository;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat reserveSeat(String id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (!seat.getStatus().equals(("LIBRE"))) {
            throw new RuntimeException("Seat not available");
        }
        seat.setStatus(("RESERVADO"));

        return seatRepository.save(seat);
    }
}
