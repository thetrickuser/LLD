package com.tuf.parkinglot.repository;

import com.tuf.parkinglot.domain.ParkingSlot;
import com.tuf.parkinglot.domain.VehicleType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParkingSlotRepository {
    private Map<UUID, ParkingSlot> parkingSlots = new ConcurrentHashMap<>();

    public ParkingSlot save(ParkingSlot slot) {
        return parkingSlots.put(slot.getId(), slot);
    }

    public Optional<ParkingSlot> findById(UUID slotId) {
        return Optional.ofNullable(parkingSlots.get(slotId));
    }

    public List<ParkingSlot> findAvailableSlots(VehicleType vehicleType) {
        return parkingSlots.values().stream()
                .filter(slot -> vehicleType == slot.getSlotType() && !slot.isOccupied())
                .toList();
    }

    public Optional<ParkingSlot> allocateSlot(VehicleType vehicleType) {
        return parkingSlots.values().stream()
                .filter(slot -> vehicleType == slot.getSlotType() && !slot.isOccupied())
                .findFirst()
                .map(slot -> {
                    slot.setOccupied(true);
                    return slot;
                });
    }

    public void releaseSlot(UUID slotId) {
        parkingSlots.computeIfPresent(slotId, (id, slot) -> {
            slot.setOccupied(false);
            return slot;
        });
    }

    public Map<VehicleType, Long> getSlotStatistics() {
        return parkingSlots.values().stream()
                .collect(Collectors.groupingBy(
                        ParkingSlot::getSlotType,
                        Collectors.counting()
                ));
    }

    public void clear() {
        parkingSlots.clear();
    }
}
