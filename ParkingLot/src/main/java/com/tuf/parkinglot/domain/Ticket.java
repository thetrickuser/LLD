package com.tuf.parkinglot.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
public class Ticket {

    private final UUID id;
    private final UUID vehicleId;
    private final UUID slotId;
    private final LocalDateTime entryTime;
    private boolean isActive;

    public Ticket(UUID vehicleId, UUID slotId) {
        this.id = UUID.randomUUID();
        this.vehicleId = vehicleId;
        this.slotId = slotId;
        this.isActive = true;
        this.entryTime = LocalDateTime.now();
    }

    public void deactivate() {
        isActive = false;
    }
}
