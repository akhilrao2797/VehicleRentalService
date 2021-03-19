package com.vehicle.rental.service;

import com.vehicle.rental.model.*;

import java.util.*;

public class BranchService {
    private static Map<String, Branch> branchMap = new HashMap<>();
    private static BranchService branchService = null;

    public static BranchService getInstance(){
        if(branchService == null){
            synchronized (BranchService.class){
                branchService = new BranchService();
            }
        }
        return branchService;
    }
    public String addBranch(String branchName) {
        Branch branch = new Branch(branchName);
        branchMap.put(branchName, branch);
        return branch.getId();
    }

    public Branch getBranch(String branchName){
        return branchMap.getOrDefault(branchName, null);
    }

    public String addVehicle(String vehicleId, VehicleType vehicleType, String branchName) {
        Branch branch = getBranch(branchName);
        if(branch.getVehicleTypeMap().containsKey(vehicleType))
            branch.getVehicleTypeMap().get(vehicleType).add(createVehicle(vehicleType, vehicleId));
        else {
            branch.getVehiclePriceMap().put(vehicleType, 0D);
            List<Vehicle> vehicles = new ArrayList<>();
            vehicles.add(createVehicle(vehicleType, vehicleId));
            branch.getVehicleTypeMap().put(vehicleType, vehicles);
        }
        return null;
    }

    private Vehicle createVehicle(VehicleType vehicleType, String vehicleId){
        Vehicle vehicle = null;
        switch(vehicleType){
            case SUV: vehicle = new SUVVehicle(); break;
            case HATCHBACK: vehicle = new HatchBackVehicle(); break;
            case SEDAN: vehicle = new SedanVehicle(); break;
            default: vehicle = null;
        }
        if(vehicle!=null){
            vehicle.setVehicleId(vehicleId);
        }
        return vehicle;
    }

    public void updatePriceOfVehicleType(String branchName, VehicleType vehicleType, double price) {
        Branch branch = getBranch(branchName);
        if(branch.getVehiclePriceMap().containsKey(vehicleType))
            branch.getVehiclePriceMap().replace(vehicleType, price);
        else {
            branch.getVehiclePriceMap().put(vehicleType, price);
        }
    }

    public static Map<String, Branch> getBranchMap() {
        return branchMap;
    }
}
