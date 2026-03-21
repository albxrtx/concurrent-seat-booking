package com.example.concurrent_seat_booking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.concurrent_seat_booking.model.Seat;
import com.example.concurrent_seat_booking.repository.SeatRepository;
import com.example.concurrent_seat_booking.service.SeatService;

@RestController
@RequestMapping("/seats")
@CrossOrigin
public class SeatController {

    private final SeatRepository seatRepository;
    private final SeatService seatService;

    public SeatController(SeatRepository seatRepository, SeatService seatService) {
        this.seatService = seatService;
        this.seatRepository = seatRepository;
    }

    @GetMapping
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @PostMapping("/{id}")
    public Seat reserveSeat(@PathVariable String id) {
        return seatService.reserveSeat(id);
    }
}
