package com.example.concurrent_seat_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.concurrent_seat_booking.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, String> {

}