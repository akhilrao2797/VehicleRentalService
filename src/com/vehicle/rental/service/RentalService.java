package com.vehicle.rental.service;

import com.vehicle.rental.model.VehicleType;

import java.time.LocalDateTime;

public class RentalService {
    private static BranchService branchService = BranchService.getInstance();
    private static BookingService bookingService = BookingService.getInstance();

    public String addBranch(String branchName){
        return branchService.addBranch(branchName);
    }

    public String addVehicle(String vehicleId, VehicleType vehicleType, String branchName){
        if(branchService.getBranch(branchName) != null)
            return branchService.addVehicle(vehicleId, vehicleType, branchName);
        return null;
    }

    public void allocatePrice(String branchName, VehicleType vehicleType, double price){
        if(branchService.getBranch(branchName) != null)
            branchService.updatePriceOfVehicleType(branchName, vehicleType, price);

    }

    public String bookVehicle(VehicleType vehicleType, LocalDateTime startTime, LocalDateTime endTime){
        return bookingService.bookVehicle(vehicleType, startTime, endTime);
    }
}
