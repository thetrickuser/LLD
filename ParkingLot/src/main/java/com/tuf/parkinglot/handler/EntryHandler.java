package com.tuf.parkinglot.handler;

import com.tuf.parkinglot.domain.ParkingSlot;
import com.tuf.parkinglot.domain.Ticket;
import com.tuf.parkinglot.domain.Vehicle;
import com.tuf.parkinglot.domain.VehicleType;
import com.tuf.parkinglot.dto.EntryResult;
import com.tuf.parkinglot.service.SlotService;
import com.tuf.parkinglot.service.TicketService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class EntryHandler {

    private final TicketService ticketService;
    private final SlotService slotService;

    public EntryHandler(TicketService ticketService, SlotService slotService) {
        this.ticketService = ticketService;
        this.slotService = slotService;
        log.info("Entry handler initialized");
    }

    public EntryResult enterVehicle(String licensePlate, VehicleType vehicleType) {
        log.info("Vehicle entry request. Reg no: {}, Vehicle type: {}", licensePlate, vehicleType);
        try {
            Vehicle vehicle = new Vehicle(licensePlate, vehicleType);
            Optional<ParkingSlot> slot = slotService.allocateSlot(vehicleType);

            if (slot.isEmpty()) {
                log.error("No slots available for vehicle type: {}", vehicleType);
                return new EntryResult(false, null, null, "No slots available");
            }

            ParkingSlot parkingSlot = slot.get();
            Ticket ticket = ticketService.generateTicket(vehicle, parkingSlot.getId());
            log.info("Vehicle entry successful. Ticket id: {}, Floor: {}, Slot id: {}",
                    ticket.getId(), parkingSlot.getFloorNumber(), parkingSlot.getId());
            return new EntryResult(true, ticket.getId(), parkingSlot.getId(), "Vehicle entry successful");
        } catch (Exception e) {
            log.error("Vehicle entry failed: {}", e.getMessage());
            return new EntryResult(false, null, null, "Vehicle entry failed");
        }
    }
}
