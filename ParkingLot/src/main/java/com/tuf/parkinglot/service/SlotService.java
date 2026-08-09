package com.tuf.parkinglot.service;

import com.tuf.parkinglot.domain.ParkingSlot;
import com.tuf.parkinglot.domain.VehicleType;
import com.tuf.parkinglot.repository.ParkingSlotRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class SlotService {

    private final ParkingSlotRepository slotRepository;

    public SlotService(ParkingSlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public Optional<ParkingSlot> allocateSlot(VehicleType vehicleType) {
        log.info("Allocating slot for vehicle type: {}", vehicleType);
        return slotRepository.allocateSlot(vehicleType);
    }

    public void releaseSlot(UUID slotId) {
        log.info("Releasing slot: {}", slotId);
        slotRepository.releaseSlot(slotId);
        log.info("Slot release successfully");
    }

    public ParkingSlot createSlot(VehicleType slotType, int floorNumber) {
        log.info("Creating new slot for {} on floor {}", slotType, floorNumber);
        ParkingSlot slot = new ParkingSlot(slotType, floorNumber);
        slotRepository.save(slot);
        log.info("Slot created successfully");
        return slot;
    }

    public int getAvailableSlotsCount(VehicleType vehicleType) {
        return slotRepository.findAvailableSlots(vehicleType).size();
    }
}
