package com.tuf.parkinglot.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Vehicle {

    private final UUID id;
    private final String licensePlate;
    private final VehicleType vehicleType;

    public Vehicle(String licensePlate, VehicleType vehicleType) {
        this.id = UUID.randomUUID();
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }
}
