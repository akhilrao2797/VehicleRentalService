package com.vehicle.rental.model;

public abstract class Vehicle {
    String vehicleId;
    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId){
        this.vehicleId = vehicleId;
    }
}
