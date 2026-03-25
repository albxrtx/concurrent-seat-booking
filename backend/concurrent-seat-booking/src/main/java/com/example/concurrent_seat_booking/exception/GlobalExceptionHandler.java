package com.example.concurrent_seat_booking.exception;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ERROR 404 -> ASIENTO NO EXISTE
    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<String> handleNotFount(SeatNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    // ERROR 409 -> ASIENTO YA RESERVADO
    @ExceptionHandler(SeatAlreadyReservedException.class)
    public ResponseEntity<String> handleConflict(SeatAlreadyReservedException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    // ERROR 409 -> CONFLICTO POR CONCURRENCIA
    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<String> handleOptimisticLocking() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("ASIENTO YA MODIFICADO POR OTRA PETICIÓN");
    }

    // ERROR 500 -> ERROR INESPERADO
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericEntity() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR INTERNO DEL SERVIDOR");
    }
}