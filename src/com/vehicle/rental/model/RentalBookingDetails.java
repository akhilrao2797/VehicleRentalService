package com.vehicle.rental.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class RentalBookingDetails {
    private String bookingId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private VehicleType vehicleType;
    private String vehicleId;

    public RentalBookingDetails(LocalDateTime startTime, LocalDateTime endTime, VehicleType vehicleType, String vehicleId) {
        this.bookingId = UUID.randomUUID().toString().replace("-","");
        this.startTime = startTime;
        this.endTime = endTime;
        this.vehicleType = vehicleType;
        this.vehicleId = vehicleId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleId() {
        return vehicleId;
    }
}
