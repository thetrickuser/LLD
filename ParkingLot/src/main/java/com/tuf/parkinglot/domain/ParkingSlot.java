package com.tuf.parkinglot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class ParkingSlot {

    private final UUID id;
    private final VehicleType slotType;
    @Setter
    private boolean isOccupied;
    private final int floorNumber;

    public ParkingSlot(VehicleType slotType, int floorNumber) {
        this.id = UUID.randomUUID();
        this.slotType = slotType;
        this.isOccupied = false;
        this.floorNumber = floorNumber;
    }

}
