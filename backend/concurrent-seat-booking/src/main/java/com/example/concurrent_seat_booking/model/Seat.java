package com.example.concurrent_seat_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Seat {
    @Id
    private String id;

    private String status;

    public Seat() {
    }

    public Seat(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
