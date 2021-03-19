package com.vehicle.rental.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Branch {
    private String id;
    private String name;
    private Map<VehicleType, List<Vehicle>> vehicleTypeMap;
    private Map<VehicleType, Double> vehiclePriceMap;

    public Branch(String name) {
        this.id = UUID.randomUUID().toString().replace("-","");
        this.name = name;
        this.vehicleTypeMap = new HashMap<>();
        this.vehiclePriceMap = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<VehicleType, List<Vehicle>> getVehicleTypeMap() {
        return vehicleTypeMap;
    }

    public Map<VehicleType, Double> getVehiclePriceMap() {
        return vehiclePriceMap;
    }

    public int getCountOfVehiclesOnType(VehicleType vehicleType){
        if(vehicleTypeMap.containsKey(vehicleType))
            return vehicleTypeMap.get(vehicleType).size();
        return 0;
    }
}
