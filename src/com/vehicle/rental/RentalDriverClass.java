package com.vehicle.rental;

import com.vehicle.rental.model.VehicleType;
import com.vehicle.rental.service.RentalService;

import java.time.LocalDateTime;

public class RentalDriverClass {
    public static void main(String[] args) {
        System.out.println("Vehicle Rental Service");
        RentalService rentalService = new RentalService();
        rentalService.addBranch("abc");
        rentalService.addVehicle("1", VehicleType.SUV, "abc");
        rentalService.addVehicle("2", VehicleType.SUV, "abc");
        rentalService.addVehicle("3", VehicleType.HATCHBACK, "abc");
        rentalService.allocatePrice("abc", VehicleType.SUV, 987);
        rentalService.allocatePrice("abc", VehicleType.HATCHBACK, 876);
        rentalService.addBranch("abcd");
        rentalService.addVehicle("1", VehicleType.SUV, "abcd");
        rentalService.addVehicle("2", VehicleType.SUV, "abcd");
        rentalService.addVehicle("3", VehicleType.SEDAN, "abcd");
        rentalService.allocatePrice("abc", VehicleType.SUV, 1000);
        rentalService.allocatePrice("abc", VehicleType.SEDAN, 987);
        System.out.println(rentalService.bookVehicle(VehicleType.SUV,
                LocalDateTime.of(2021,2, 20, 1, 1),
                LocalDateTime.of(2021,2, 24, 1, 1)));
        System.out.println(rentalService.bookVehicle(VehicleType.SUV,
                LocalDateTime.of(2021,2, 20, 1, 1),
                LocalDateTime.of(2021,2, 24, 1, 1)));
    }
}
