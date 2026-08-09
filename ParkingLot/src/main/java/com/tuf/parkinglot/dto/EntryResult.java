package com.tuf.parkinglot.dto;

import com.tuf.parkinglot.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class EntryResult {

    private final boolean success;
    private final UUID ticketId;
    private final UUID slotId;
    private final String message;
}
