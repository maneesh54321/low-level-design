package com.learning.vehicle;

public class Vehicle {
    private String regNo;
    private VehicleType type;

    public Vehicle(String regNo, VehicleType type) {
        this.regNo = regNo;
        this.type = type;
    }

    public String getRegNo() {
        return regNo;
    }

    public VehicleType getType() {
        return type;
    }
}
