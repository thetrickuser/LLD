package com.tuf.parkinglot.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Floor {

    private final UUID id;
    @Getter
    private final int floorNumber;
    @Getter
    private List<ParkingSlot> parkingSlots;

    public Floor(int floorNumber) {
        this.id = UUID.randomUUID();
        this.floorNumber = floorNumber;
        this.parkingSlots = new ArrayList<>();
    }

    public int getTotalSlots() {
        return parkingSlots.size();
    }

    public void addSlot(ParkingSlot slot) {
        parkingSlots.add(slot);
    }

    public List<ParkingSlot> getAvailableSlots(VehicleType vehicleType) {
        return parkingSlots.stream()
                .filter(slot -> !slot.isOccupied() && slot.getSlotType() == vehicleType)
                .toList();
    }

    public int getAvailableSlotsCount(VehicleType vehicleType) {
        return getAvailableSlots(vehicleType).size();
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                ", floorNumber=" + floorNumber +
                ", totalSlots=" + parkingSlots.size() +
                '}';
    }
}
