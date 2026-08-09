package com.tuf.parkinglot.service;

import com.tuf.parkinglot.domain.Ticket;
import com.tuf.parkinglot.domain.Vehicle;
import com.tuf.parkinglot.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository repository) {
        ticketRepository = repository;
    }

    public Ticket generateTicket(Vehicle vehicle, UUID slotId) {
        log.info("Generating ticket for vehicle: {}", vehicle.getLicensePlate());

        Ticket ticket = new Ticket(vehicle.getId(), slotId);
        ticketRepository.save(ticket);

        log.info("Ticket generated successfully with ticket id: {}", ticket.getId());
        return ticket;
    }

    public Optional<Ticket> getTicket(UUID ticketId) {
        log.info("Retrieving ticket: {}", ticketId);
        return ticketRepository.findById(ticketId);
    }

    public void deactivateTicket(UUID ticketId) {
        log.info("Deactivating ticket: {}", ticketId);
        ticketRepository.deactivateTicket(ticketId);
    }
}
