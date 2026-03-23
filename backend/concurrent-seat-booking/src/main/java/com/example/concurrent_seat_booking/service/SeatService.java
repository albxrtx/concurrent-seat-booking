package com.example.concurrent_seat_booking.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.concurrent_seat_booking.model.Seat;
import com.example.concurrent_seat_booking.repository.SeatRepository;

import jakarta.transaction.Transactional;

@Service
public class SeatService {

    // todo Añadir WebSockets para mostra en tiempo real las reservas

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    // @Transactional
    // public Seat reserveSeat(String id) {
    // Seat seat = seatRepository.findById(id)
    // .orElseThrow(() -> new RuntimeException("Seat not found"));

    // if (!seat.getStatus().equals(("LIBRE"))) {
    // throw new RuntimeException("Seat not available");
    // }

    // seat.setStatus(("RESERVADO"));
    // seatRepository.save(seat);

    // try {
    // return seatRepository.save(seat);
    // } catch (OptimisticLockingFailureException e) {
    // throw e;
    // }

    // }
    @Transactional
    public Seat reserveSeat(String id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new RuntimeException("ASIENTO NO ENCONTRADO"));
        if (!seat.getStatus().equals(("LIBRE"))) {
            throw new RuntimeException("ASIENTO NO DISPONIBLE");
        }
        seat.setStatus("PENDIENTE");
        seatRepository.save(seat);

        messagingTemplate.convertAndSend("/topic/seats", seat);

        confirmReservation(id);
        return seat;
    }

    @Async
    public void confirmReservation(String id) {
        try {
            Thread.sleep(2000);
            Seat seat = seatRepository.findById(id).orElseThrow(() -> new RuntimeException("ASIENTO NO ENCONTRADO"));
            if (seat.getStatus().equals("PENDIENTE")) {
                seat.setStatus("RESERVADO");
                messagingTemplate.convertAndSend("/topic/seats", seat);
                seatRepository.save(seat);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
