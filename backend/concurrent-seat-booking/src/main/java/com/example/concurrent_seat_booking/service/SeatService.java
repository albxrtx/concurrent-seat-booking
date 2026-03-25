package com.example.concurrent_seat_booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.concurrent_seat_booking.exception.SeatAlreadyReservedException;
import com.example.concurrent_seat_booking.exception.SeatNotFoundException;
import com.example.concurrent_seat_booking.model.Seat;
import com.example.concurrent_seat_booking.repository.SeatRepository;

import jakarta.transaction.Transactional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Transactional
    public Seat reserveSeat(String id) {
        // Obtenemos el asiento mediante el ID
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException("ASIENTO NO ENCONTRADO"));
        // Comprobamos que el asiento este disponible
        if (!seat.getStatus().equals(("LIBRE"))) {
            throw new SeatAlreadyReservedException("ASIENTO YA RESERVADO");
        }
        /*
         * Cambiamos el estado del asiento a uno intermedio,
         * notificamos al frontend mediante WebSockets
         * y guardamos el asiento
         */
        seat.setStatus("PENDIENTE");
        messagingTemplate.convertAndSend("/topic/seats", seat);
        seatRepository.save(seat);

        // LLamamos a la función para confirmar la reserva
        confirmReservation(id);
        return seat;
    }

    /*
     * Método asíncrono para confirmar la reserva
     * Cambia el estado del asiento a "RESERVADO"
     */

    @Async
    public void confirmReservation(String id) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        // Obtenemos el asiento mediante el ID
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new SeatNotFoundException("ASIENTO NO ENCONTRADO"));
        // Se comprueba si el asiento está pendiente
        if (seat.getStatus().equals("PENDIENTE")) {
            /*
             * Cambiamos el estado del asiento,
             * notificamos al frontend mediante WebSockets
             * y guardamos el asiento
             */
            seat.setStatus("RESERVADO");
            messagingTemplate.convertAndSend("/topic/seats", seat);
            seatRepository.save(seat);
        }
    }

}
