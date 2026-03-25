package com.example.concurrent_seat_booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

/*
    Modelo del asiento
*/
@Entity
public class Seat {
    @Id
    private String id;

    private String status;

    @Version
    private Long version;

    /*
     * Constructores
     */

    public Seat() {
    }

    public Seat(String id, String status, Long version) {
        this.id = id;
        this.status = status;
        this.version = version;
    }

    /*
     * Getters y Setters
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
