package com.vehicle.rental.service;

import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.RentalBookingDetails;
import com.vehicle.rental.model.Vehicle;
import com.vehicle.rental.model.VehicleType;

import java.time.LocalDateTime;
import java.util.*;

public class BookingService {
    private static List<RentalBookingDetails> bookingHistory = new ArrayList<>();

    public String bookVehicle(VehicleType vehicleType, LocalDateTime startTime,  LocalDateTime endTime){
        RentalBookingDetails rentalBookingDetails = null;
        Map<Double, List<Vehicle>> vehiclesAcrossBranches = getVehiclesOfParticularType(vehicleType);
        LoopForVehicle:
        for(Map.Entry<Double, List<Vehicle>> vehicles: vehiclesAcrossBranches.entrySet()){
            for(Vehicle vehicle: vehicles.getValue()){
                synchronized (BookingService.class) {
                    if (checkIfAvailable(vehicle, startTime, endTime)) {
                        rentalBookingDetails = new RentalBookingDetails(startTime, endTime, vehicleType, vehicle.getVehicleId());
                        bookingHistory.add(rentalBookingDetails);
                        break LoopForVehicle;
                    }
                }
            }
        }
        if(rentalBookingDetails != null)
            return new StringBuffer(rentalBookingDetails.getBookingId())
                    .append(" ")
                    .append(rentalBookingDetails.getVehicleId())
                    .append(" ")
                    .append(rentalBookingDetails.getVehicleType())
                    .toString();
        return "Booking Not Successful";
    }

    private boolean checkIfAvailable(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        boolean available = true;
        for(RentalBookingDetails bookingOrder : getBookingHistory()){
            if(vehicle.getVehicleId().equalsIgnoreCase(bookingOrder.getVehicleId())
                    && ((startTime.isBefore(bookingOrder.getEndTime()) && startTime.isAfter(bookingOrder.getStartTime())
                    || (endTime.isAfter(bookingOrder.getStartTime()) && endTime.isBefore(bookingOrder.getEndTime()))))){
                available = false;
                break;
            }
        }
        return available;
    }

    private Map<Double, List<Vehicle>> getVehiclesOfParticularType(VehicleType vehicleType) {
        Map<Double, List<Vehicle>> vehicles = new TreeMap<>();
        for(Map.Entry<String, Branch> branch: BranchService.getBranchMap().entrySet()){
            if(branch.getValue().getVehiclePriceMap().containsKey(vehicleType))
                vehicles.put(branch.getValue().getVehiclePriceMap().get(vehicleType),
                    branch.getValue().getVehicleTypeMap().get(vehicleType));
        }
        return vehicles;
    }

    public static List<RentalBookingDetails> getBookingHistory() {
        return bookingHistory;
    }

    private static BookingService bookingService = null;
    public static BookingService getInstance(){
        if(bookingService == null){
            synchronized (BookingService.class){
                bookingService = new BookingService();
            }
        }
        return bookingService;
    }
}
